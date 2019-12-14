package FedirkoMykhenvych.A2;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public final class ErrorPrinter{
	private static int errorCount = 0;
	
	
	private static void reportError() {
		errorCount++;
	}
	
    public static void printFullError(Recognizer recognizer, Token offendingToken, String message, String symbol, String location){
        ErrorPrinter.printFileNameAndLineNumber(offendingToken);
        System.err.println(message);
        ErrorPrinter.underlineError(recognizer, offendingToken);
        System.err.println("  " + symbol);
        System.err.println("  in " + location + "()");
    }
    
    public static void printFileNameAndLineNumber(Token offendingToken){
        reportError();
        System.err.print("\n" + ExpressionsMain.getFileName() + ": " +offendingToken.getLine()+": ");

    }
    
    public static void printSymbolAlreadyDefinedError(Recognizer recognizer, Token offendingToken, String symbolType, String symbol){
        ErrorPrinter.printFileNameAndLineNumber(offendingToken);
        System.err.println("error: " + symbolType + " " + symbol + " already defined");
        ErrorPrinter.underlineError(recognizer, offendingToken);
    }
    
    public static void underlineError(Recognizer recognizer, Token offendingToken) {
        int line = offendingToken.getLine();
        int charPositionInLine = offendingToken.getCharPositionInLine();
        BufferedTokenStream tokens = (BufferedTokenStream)recognizer.getInputStream();
        String input = tokens.getTokenSource().getInputStream().toString();
        String[] lines = input.split("\n");
        String errorLine = lines[line - 1];
        System.err.println(errorLine);
        for (int i=0; i<charPositionInLine; i++){
            if(errorLine.charAt(i)=='\t'){
                System.err.print("\t");
            }else{
                System.err.print(" ");
            }
        }

        System.err.println();
    }
}