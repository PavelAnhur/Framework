package page.googlecloud;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudPage {
	
	private String GOOGLE_CLOUD_PAGE_URL = "https://cloud.google.com/";
	private String SEARCH_TERM_FOR_GOOGLE_CLOUD_PAGE = "Google Cloud Platform Pricing Calculator";
	
	@FindBy(xpath = "//input[@name='q']")
	public WebElement searchField;
	
	public GoogleCloudPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getGoogleCloudPageUrl() {
		return GOOGLE_CLOUD_PAGE_URL;
	}
	
	public void setGoogleCloudPageUrl(String googleCloudPageUrl) {
		this.GOOGLE_CLOUD_PAGE_URL = googleCloudPageUrl;
	}
	
	public String getSearchTermForGoogleCloudPage() {
		return SEARCH_TERM_FOR_GOOGLE_CLOUD_PAGE;
	}
	
	public void setSearchTermForGoogleCloudPage(String searchTermForGoogleCloudPage) {
		this.SEARCH_TERM_FOR_GOOGLE_CLOUD_PAGE = searchTermForGoogleCloudPage;
	}
}
