package tokenizer;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Tokenizer {
	private static final String[] SYNTAX = {"resources/languages/English", "resources/languages/Syntax"};

	private List<TokenPatterns> patterns;
	private String toTokenize;
	private String[] tokenArray;
	private int index;

	public Tokenizer(String s) {
		toTokenize = s;
		fillPatterns();
		breakUp();
	}

	private void fillPatterns() {
		patterns = new ArrayList<>();
		for (int i = 0; i < SYNTAX.length; i++) {
			ResourceBundle resources = ResourceBundle.getBundle(SYNTAX[i]);
			Enumeration<String> iter = resources.getKeys();
			while (iter.hasMoreElements()) {
				String key = iter.nextElement();
				String regex = resources.getString(key);
				patterns.add(new TokenPatterns(Pattern.compile(regex, Pattern.CASE_INSENSITIVE), key));
			}
		}
	}

	private void breakUp() {
		toTokenize = toTokenize.trim();
		tokenArray = toTokenize.split("\\s+|\n");
	}

	public TokenIdentifier getToken() {
		toTokenize = tokenArray[index];
		for (TokenPatterns p : patterns) {
			if (match(toTokenize, p.getPattern())) {
				index++;
				return new TokenIdentifier(toTokenize, p.getTokenTypes());
			}
		}
		throw new IllegalStateException("Could not identify: " + toTokenize);
	}

	public boolean isEmpty() {
		return index == tokenArray.length;
	}

	private boolean match(String text, Pattern regex) {
		return regex.matcher(text).matches();
	}
	
	

}
