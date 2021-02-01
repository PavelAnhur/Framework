package step;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.tenminutesmail.TenMinutesMailPage;
import util.StringUtils;

import java.util.ArrayList;

public class StepsOnTenMinutesMailPage {
	
	private final WebDriver webDriver;
	private String eMailAddress;
	private TenMinutesMailPage tenMinutesMailPage;
	private final String tenMinutesMailAddress = "https://www.minuteinbox.com/";
	private static ArrayList<String> tabs;
	
	public StepsOnTenMinutesMailPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		eMailAddress = "";
	}
	
	public StepsOnTenMinutesMailPage addTenMinutesEMailTab() {
		((JavascriptExecutor) webDriver).executeScript("window.open('" + tenMinutesMailAddress +"','_blank')");
		tabs = new ArrayList<>(webDriver.getWindowHandles());
		return this;
	}
	
	public void getMailAddressFromTenMinutesMailSite() {
		webDriver.switchTo().window(tabs.get(1));
		eMailAddress = StringUtils.getEMailAddressAsString(tenMinutesMailPage);
	}
	
	public String getEMailAddress() {
		return eMailAddress;
	}
	
	public void getMailOnTenMinutesMailBox() {
		webDriver.switchTo().window(tabs.get(1));
		tenMinutesMailPage.inboxCount = new WebDriverWait(webDriver, 60)
			.until(ExpectedConditions.visibilityOf(tenMinutesMailPage.inboxCount));
		tenMinutesMailPage.inboxCount.click();
	}
	
	public String getTextFromMail() {
		return tenMinutesMailPage.estimateCostInMail.getText();
	}
	
	public String getEstimateCostFromMail() {
		return StringUtils.getNumberFromString(getTextFromMail());
	}
}
