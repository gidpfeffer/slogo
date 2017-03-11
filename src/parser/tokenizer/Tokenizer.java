package parser.tokenizer;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import controller.SLogoException;


public class Tokenizer {
	private String[] syntax = {"", "resources/languages/syntax"};

	private List<TokenEntry<Pattern, String>> patterns;
	private String toTokenize;
	private String[] tokenArray;
	private int index;

	public Tokenizer(String s, String language) {
		syntax[0] = language;
		toTokenize = s;
		fillPatterns();
		breakUp();
	}

	private void fillPatterns() {
		patterns = new ArrayList<>();
		for (int i = 0; i < syntax.length; i++) {
			ResourceBundle resources = ResourceBundle.getBundle(syntax[i]);
			Enumeration<String> iter = resources.getKeys();
			while (iter.hasMoreElements()) {
				String key = iter.nextElement();
				String regex = resources.getString(key);
				TokenEntry<Pattern, String> entry = 
						new TokenEntry<>(Pattern.compile(regex, Pattern.CASE_INSENSITIVE), key);
				patterns.add(entry);
			}
		}
	}

	private void breakUp() {
		toTokenize = toTokenize.trim();
		tokenArray = toTokenize.split("\\s+|\n");
	}

	public TokenEntry<String, String> getToken() {
		toTokenize = tokenArray[index];
		for (TokenEntry<Pattern, String> t : patterns) {
			if (match(toTokenize, t.getKey())) {
				index++;
				TokenEntry<String, String> entry = new TokenEntry<>(toTokenize, t.getValue());
				return entry;
			}
		}
		throw new SLogoException("Could not identify: " + toTokenize);
	}

	public boolean isEmpty() {
		return index == tokenArray.length;
	}

	private boolean match(String text, Pattern regex) {
		return regex.matcher(text).matches();
	}
}
