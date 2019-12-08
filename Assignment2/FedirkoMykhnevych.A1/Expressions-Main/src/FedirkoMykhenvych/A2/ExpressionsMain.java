package FedirkoMykhenvych.A2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import FedirkoMykhnevych.A2.*;

public class ExpressionsMain {

	private static String inputFile = null;

	public static void main(String[] args) throws Exception {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);

		for (int i = 0; i < args.length; i++)
			runFile(args[i]);

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

		TypeCheckingVisitor typeChecker = new TypeCheckingVisitor(scopes, parser);
		typeChecker.visit(root);
	}

	public static String getFileName() {
		return inputFile;
	}

}
