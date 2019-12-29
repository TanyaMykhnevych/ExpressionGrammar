package FedirkoMykhnevych.A2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import FedirkoMykhnevych.A3.PythonCodeGenerator;
import FedirkoMykhnevych.A4.BytecodeGenerator;

public class ExpressionsMain {

	private static String inputFile = null;
	private final static List<String> INPUT_EXTENSIONS = Arrays.asList("ofp");

	public static void main(String[] args) throws Exception {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);

		for (int i = 0; i < args.length; i++) {
			String extension = getExtensionByStringHandling(args[i]);
			if (INPUT_EXTENSIONS.contains(extension)) {
				runFile(args[i]);
			} else {
				System.out.println("Unsupported file extension: " + extension);
				return;
			}
		}

		System.out.println("Done!");
	}

	public static void runFile(String path) throws Exception {
		inputFile = path;
		System.out.println("Reading test program from: " + path);

		// Parse input program
		OfpLexer lexer = new OfpLexer(CharStreams.fromFileName(path));
		OfpParser parser = new OfpParser(new BufferedTokenStream(lexer));
		OfpParser.StartContext root = parser.start();

		Map<String, Function> declaredFunctions = new HashMap<String, Function>();
		ParseTreeProperty<Scope> scopes = new ParseTreeProperty<Scope>();

		SymbolTableListener fl = new SymbolTableListener(declaredFunctions, scopes, parser);
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(fl, root);

		CheckRefListener rl = new CheckRefListener(declaredFunctions, scopes);
		walker.walk(rl, root);

		TypeCheckingVisitor typeChecker = new TypeCheckingVisitor(declaredFunctions, scopes, parser);
		typeChecker.visit(root);

		System.out.println("Total errors count: " + ErrorPrinter.errorCount());

		// processPythonCode(declaredFunctions, scopes, root);

		Path inputFilePath = Paths.get(inputFile);
		String progName = inputFilePath.getFileName().toString().replaceAll("\\.ofp$", "");
		System.out.println("\nBytecode generation started\n");
		BytecodeGenerator bcGen = new BytecodeGenerator(declaredFunctions, scopes);
		bcGen.visit(root);
		System.out.println("\nPrint bytecode\n");
		byte[] code = bcGen.getByteArray();
		ClassReader cr = new ClassReader(code);
		ClassVisitor tracer = new TraceClassVisitor(new PrintWriter(System.out));
		ClassVisitor checker = new CheckClassAdapter(tracer, true);
		cr.accept(checker, 0);
		File javaOutFile = new File("test_class_files/" + progName + ".class");
		FileOutputStream fos = new FileOutputStream(javaOutFile);
		fos.write(code);
		fos.close();
		System.out.println("Bytecode saved in " + javaOutFile.getAbsolutePath());
		System.out.println("\nExecuting bytecode");
		/*
		 * Main loader = new Main(); Class<?> exampleClass =
		 * loader.defineClass(progName, code, 0, code.length);
		 * exampleClass.getMethods()[0].invoke(null, new Object[] { null });
		 */

	}

	public static String getFileName() {
		return inputFile;
	}

	private static String getExtensionByStringHandling(String filePath) {
		return filePath.substring(filePath.lastIndexOf(".") + 1);
	}

	private static void processPythonCode(Map<String, Function> declaredFunctions, ParseTreeProperty<Scope> scopes,
			OfpParser.StartContext root) {
		PythonCodeGenerator pyGen = new PythonCodeGenerator(declaredFunctions, scopes);

		String pyProgram = pyGen.visit(root);
		String pyOutputPath = inputFile.replaceAll("\\.ofp$", ".py");
		try {
			PrintWriter pw = new PrintWriter(pyOutputPath);
			pw.print(pyProgram);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\nPython program saved as: " + pyOutputPath);
	}

}
