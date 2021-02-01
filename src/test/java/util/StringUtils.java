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
	
	public static String getEMailAddressAsString(TenMinutesMailPage tenMinutesMailPage) {
		tenMinutesMailPage = new TenMinutesMailPage(WebDriverSetup.getDriver());
		tenMinutesMailPage.eMailAddress.click();
		String resultMailAddress = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		boolean hasStringText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		if (hasStringText) {
			try {
				resultMailAddress = (String) contents.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException | IOException ex) {
				System.out.println(ex);
				ex.printStackTrace();
			}
		}
		return resultMailAddress;
	}
}