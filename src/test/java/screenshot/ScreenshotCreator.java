package screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import webdriver.WebDriverSetup;

import java.io.File;
import java.io.IOException;

public class ScreenshotCreator {

	public static void getScreenshot() {
		File screenshotFile = ((TakesScreenshot) WebDriverSetup.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile, new File("reports/screenshot.png"), true);
		} catch (
				IOException e) {
			e.printStackTrace();
		}
	}
}
