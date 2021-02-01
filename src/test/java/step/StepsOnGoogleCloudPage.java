package step;

import org.openqa.selenium.WebDriver;
import page.googlecloud.GoogleCloudPage;

public class StepsOnGoogleCloudPage {
	
	private final WebDriver webDriver;
	private GoogleCloudPage googleCloudPage;
	
	public StepsOnGoogleCloudPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		webDriver.manage().window().maximize();
	}
	
	public StepsOnGoogleCloudPage openGoogleCloudPage() {
		googleCloudPage = new GoogleCloudPage(webDriver);
		webDriver.get(googleCloudPage.getGoogleCloudPageUrl());
		return this;
	}
	
	public void inputSearchTerm() {
		googleCloudPage.searchField.sendKeys(googleCloudPage.getSearchTermForGoogleCloudPage());
		googleCloudPage.searchField.submit();
	}
}
