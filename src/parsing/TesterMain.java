package parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import parsing.errors.InvalidSyntax;
import parsing.simplifier.Simplifier;

public class TesterMain {
	
    // utility function that reads given file and returns its entire contents as a single string
    private String readFileToString (String filename) {
        final String END_OF_FILE = "\\z";
        Scanner input = new Scanner(getClass().getResourceAsStream(filename));
        input.useDelimiter(END_OF_FILE);
        String result = input.next();
        input.close();
        return result;
    }

    // given some text, prints results of parsing it using the given language
    private void parseText (ProgramParser lang, String[] text, List<String> literals, List<String> types) {
        for (String s : text) {
            if (s.trim().length() > 0) {
                try {
			//		System.out.println(String.format("%s : %s", s, lang.getSymbol(s)));
					literals.add(s);
					types.add(lang.getSymbol(s));
				} catch (InvalidSyntax e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        System.out.println();
    }

    public static void main (String[] args) {
        final String WHITESPACE = "\\s+";
        
    	List<String> literals = new ArrayList<>();
    	List<String> types = new ArrayList<>();

        TesterMain m = new TesterMain();
        ProgramParser lang = new ProgramParser();
        // these are more specific, so add them first to ensure they are checked first
        lang.addPatterns("resources/languages/English");
        // these are more general so add them at the end
        lang.addPatterns("resources/languages/Syntax");

        // try against different inputs
//        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        String fileInput = m.readFileToString("/examples/loops/circles.logo");
 //       m.parseText(lang, userInput.split(WHITESPACE));
        m.parseText(lang, fileInput.split(WHITESPACE), literals, types);
        Simplifier s = new Simplifier(literals, types);
    }
}
