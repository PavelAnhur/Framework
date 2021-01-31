package util.screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import util.webdriver.WebDriverSetup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotCreator {

	public static void getScreenshot() {

		StringBuffer failedScreenshotPath = new StringBuffer();
		failedScreenshotPath.append("reports/");
		failedScreenshotPath.append(new SimpleDateFormat("yyyy.MM.dd-HH.mm").format(new Date()));
		failedScreenshotPath.append("-util.screenshot.jpg");

		try {
			FileUtils.copyFile(((TakesScreenshot) WebDriverSetup.getDriver()).getScreenshotAs(OutputType.FILE)
					, new File(String.valueOf(failedScreenshotPath)), true);
		} catch (
				IOException e) {
			e.printStackTrace();
		}
	}
}