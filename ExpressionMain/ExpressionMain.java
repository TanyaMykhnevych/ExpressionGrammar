package mykhnevych.a2;

import java.io.IOException;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.ConsoleErrorListener;

public class ExpressionMain {
	public static void main(String[] args) throws Exception {
		/*
		 * if (args.length < 1) { System.err.println("Input file not provided!");
		 * System.exit(1); } String inputPath = args[0];
		 * System.out.println("\nParsing started for " + inputPath);
		 */

		String inputPath = "D:\\Tanya\\Sweden\\Code_transformation_and_interpretation\\A2\\ExpressionMain\\input.txt";
		System.out.println("Reading test program from: " + inputPath);

		// Parse input program
		ExpressionGrammarParser parser = null;
		ExpressionGrammarParser.StartContext root = null;

		ConsoleErrorListener errorListener = new ConsoleErrorListener();
		SymbolTableListener listener = new SymbolTableListener();
		try {
			CharStream inputStream = CharStreams.fromFileName(inputPath);
			ExpressionGrammarLexer lexer = new ExpressionGrammarLexer(inputStream);
			parser = new ExpressionGrammarParser(new BufferedTokenStream(lexer));
			parser.addErrorListener(errorListener);
			parser.addParseListener(listener);
			root = parser.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Display tree
		Trees.inspect(root, parser);

		System.out.println("Done!");
	}

}
