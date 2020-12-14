package test;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import screenshot.ScreenshotCreator;

public class TestListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult result) {
		super.onTestFailure(result);
		ScreenshotCreator.getScreenshot();
	}
}
