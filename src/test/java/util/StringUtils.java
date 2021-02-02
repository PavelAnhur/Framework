package util;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.WebDriver;
import page.tenminutesmail.TenMinutesMailPage;
import util.webdriver.WebDriverSetup;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class StringUtils {
	
	private final WebDriver webDriver = WebDriverSetup.getDriver();
	
	public static String getNumberFromString(String string) {
		String[] wordsFromString = string.split(" ");
		for (String word : wordsFromString) {
			if (NumberUtils.isParsable(String.valueOf(word.charAt(0)))) {
				return word;
			}
		}
		return null;
	}
}