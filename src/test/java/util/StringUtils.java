package util;

import org.apache.commons.lang3.math.NumberUtils;

public class StringUtils {
	
	public static String getCostFromString(String string) {
		String[] wordsFromString = string.split(" ");
		for (String word : wordsFromString) {
			if (NumberUtils.isParsable(String.valueOf(word.charAt(0)))) {
				return word;
			}
		}
		return null;
	}
}
