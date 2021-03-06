package page.googlecloud;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudPricingCalculatorPage {
	
	private final String locatorTemplate = "div.md-clickable md-select-menu md-content md-option[value=\"%s\"]";
	
	public String getNumberOfGPULocator() {
		return locatorTemplate;
	}
	
	public String getChooseGPUTypeLocator() {
		return locatorTemplate;
	}
	
	public String getLocalSSDNumberLocator() {
		return locatorTemplate;
	}
	
	public String getDatacenterLocationChoiceLocator() {
		return locatorTemplate;
	}
	
	public String getCommittedUsageChoiceLocator() {
		return locatorTemplate;
	}
	
	@FindBy(css = "[ng-model$='quantity']")
	public WebElement inputNumberOfInstances;
	
	@FindBy(css = "iframe[src*=calculator]")
	public WebElement calculatorIFrame;
	
	@FindBy(id = "myFrame")
	public WebElement internalCalculatorFrame;
	
	@FindBy(css = "md-select[name='series']")
	public WebElement seriesSelectField;
	
	@FindBy(css = "md-option[value=n1]")
	public WebElement seriesSelectedOptions;
	
	@FindBy(css = "md-select[aria-label*='Instance type']")
	public WebElement machineTypeSelectedField;
	
	@FindBy(css = "md-option[value$=STANDARD-8]")
	public WebElement machineTypeSelect;
	
	@FindBy(css = "div[class=md-label]")
	public WebElement addGPUCheckbox;
	
	@FindBy(css = "md-select[ng-model$=gpuCount]")
	public WebElement numberOfGPUDropdown;
	
	@FindBy(css = "div.md-clickable md-select-menu md-content md-option")
	public WebElement chooseNumberOfGPUOption;
	
	@FindBy(css = "md-select[placeholder='GPU type']")
	public WebElement gPUTypeDropdown;
	
	@FindBy(css = "div.md-clickable md-select-menu md-content md-option")
	public WebElement gPUTypeOption;
	
	@FindBy(css = "md-select[placeholder='Local SSD']")
	public WebElement localSSDDropdown;
	
	@FindBy(css = "div.md-clickable md-select-menu md-content md-option")
	public WebElement localSSDNumberOption;
	
	@FindBy(css = "md-select[placeholder=\"Datacenter location\"]")
	public WebElement datacenterLocationDropdown;
	
	@FindBy(css = "div.md-clickable md-select-menu md-content md-option")
	public WebElement datacenterLocationOption;
	
	@FindBy(css = "md-select[placeholder=\"Committed usage\"]")
	public WebElement committedUsageDropdown;
	
	@FindBy(css = "div.md-clickable md-select-menu md-content md-option")
	public WebElement committedUsageOption;
	
	@FindBy(xpath = "//button[@aria-label='Add to Estimate']")
	public WebElement addToEstimateButton;
	
	@FindBy(css = "#email_quote")
	public WebElement eMailEstimateForm;
	
	@FindBy(css = "input[ng-model$='email']")
	public WebElement eMailInputField;
	
	@FindBy(css = "b[class=ng-binding]")
	public WebElement totalCostFieldInComputeEngineForm;
	
	@FindBy(css = "button[aria-label='Send Email']")
	public WebElement sendEMail;
	
	public GoogleCloudPricingCalculatorPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
