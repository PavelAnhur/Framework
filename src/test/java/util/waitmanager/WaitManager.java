package util.waitmanager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.webdriver.WebDriverSetup;

public class WaitManager {
	private static final WebDriverWait wait = new WebDriverWait(WebDriverSetup.getDriver(), 5);
	
	private WaitManager() {
	}
	
	public static void waitForElementVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
