package step;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.tenminutesmail.TenMinutesMailPage;
import util.StringUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

public class StepsOnTenMinutesMailPage {
	
	private final WebDriver webDriver;
	private static String eMailAddressFromPage;
	private TenMinutesMailPage tenMinutesMailPage;
	private final String TEN_MINUTES_MAIL_ADDRESS = "https://www.minuteinbox.com/";
	private static ArrayList<String> tabs;
	
	public StepsOnTenMinutesMailPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public StepsOnTenMinutesMailPage addTenMinutesEMailTab() {
		((JavascriptExecutor) webDriver).executeScript("window.open('" + TEN_MINUTES_MAIL_ADDRESS +"','_blank')");
		tabs = new ArrayList<>(webDriver.getWindowHandles());
		return this;
	}
	
	public void getMailAddressFromTenMinutesMailSite() {
		webDriver.switchTo().window(tabs.get(1));
		eMailAddressFromPage = getEMailAddressAsString();
	}
	
	private String getEMailAddressAsString() {
		tenMinutesMailPage = new TenMinutesMailPage(webDriver);
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
		System.out.println(resultMailAddress);
		return resultMailAddress;
	}
	
	public void getMailOnTenMinutesMailBox() {
		webDriver.switchTo().window(tabs.get(1));
		tenMinutesMailPage.inboxCount = new WebDriverWait(webDriver, 60)
			.until(ExpectedConditions.visibilityOf(tenMinutesMailPage.inboxCount));
		tenMinutesMailPage.inboxCount.click();
	}
	public String getEstimateCostFromMail() {
		return StringUtils.getNumberFromString(getTextFromMail());
	}
	
	public static String getEMailAddressFromPage() {
		return eMailAddressFromPage;
	}
	
	public void setEMailAddressFromPage(String eMailAddressFromPage) {
		this.eMailAddressFromPage = eMailAddressFromPage;
	}
	
	private String getTextFromMail() {
		return tenMinutesMailPage.estimateCostInMail.getText();
	}
}
