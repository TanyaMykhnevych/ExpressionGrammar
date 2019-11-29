package Fedirko.A1;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;


public class ExpressionsMain {

	public static void main(String[] args) throws Exception {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);

		String testProgram = args[0];


		for(int i = 0; i < args.length; i++)
			runFile(args[i]);
		
		System.out.println("Done!");
	}
	
	public static void runFile(String path) throws Exception {
		
		System.out.println("Reading test program from: "+path);
		
		// Parse input program
		OfpLexer lexer = new OfpLexer(CharStreams.fromFileName(path));
		OfpParser parser = new OfpParser(new BufferedTokenStream(lexer));		 
		OfpParser.StartContext root = parser.start();

		// Display tree
		Trees.inspect(root, parser);
		
	}

}
