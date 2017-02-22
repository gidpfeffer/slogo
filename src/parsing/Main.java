package parsing;

import java.util.Scanner;

import parsing.errors.InvalidSyntax;


public class Main {
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
    private void parseText (ProgramParser lang, String[] text) {
        for (String s : text) {
            if (s.trim().length() > 0) {
                try {
					System.out.println(String.format("%s : %s", s, lang.getSymbol(s)));
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
        String[] examples = {
            "",
            "# foo",
            "foo #",
            "#",
            "fd",
            "FD",
            "forwardd",
            "equalp",
            "equal?",
            "equal??",
            "+",
            "SuM",
            "-",
            "*",
            "/",
            "%",
            "~",
            "+not",
            "not+",
            "++",
            "+*+",
            "or",
            "FOR",
            "allOrNothing",
            "all_or_nothing",
            "allOr_nothing?",
            "allOr?nothing_",
            ":allornothing",
            "PI",
            "90",
            "9.09",
            "9.0.0",
            "[",
            "]",
            "(",
            ")"
        };

        Main m = new Main();
        ProgramParser lang = new ProgramParser();
        // these are more specific, so add them first to ensure they are checked first
        lang.addPatterns("resources/languages/English");
        // these are more general so add them at the end
        lang.addPatterns("resources/languages/Syntax");

        // try against different inputs
        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        String fileInput = m.readFileToString("/examples/loops/circle.logo");
        m.parseText(lang, examples);
        m.parseText(lang, userInput.split(WHITESPACE));
        m.parseText(lang, fileInput.split(WHITESPACE));
    }
}
