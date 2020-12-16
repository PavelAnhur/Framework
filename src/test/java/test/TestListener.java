package test;

import org.testng.ITestListener;
import org.testng.ITestResult;
import screenshot.ScreenshotCreator;

public class TestListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		ScreenshotCreator.getScreenshot();
	}
}
