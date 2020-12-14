package screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import webdriver.WebDriverSetup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotCreator {

	public static void getScreenshot() {
		File screenshotFile = ((TakesScreenshot) WebDriverSetup.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			StringBuffer failedScreenshotPath = new StringBuffer();
			failedScreenshotPath.append("reports/");
			String currentDate = new SimpleDateFormat("yyyy.MM.dd-HH.mm").format(new Date());
			failedScreenshotPath.append(currentDate);
			failedScreenshotPath.append("-screenshot.jpg");
			FileUtils.copyFile(screenshotFile, new File(String.valueOf(failedScreenshotPath)), true);
		} catch (
				IOException e) {
			e.printStackTrace();
		}

	}
}
