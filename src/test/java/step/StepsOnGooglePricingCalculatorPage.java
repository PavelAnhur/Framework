package step;

import exception.CommittedUsageException;
import exception.GPUNumberException;
import exception.LocalSSDNumberException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.googlecloud.GoogleCloudPricingCalculatorPage;
import page.googlecloud.GoogleCloudResultPage;
import property.PropertyDataReader;
import util.StringUtils;
import valueobject.GoogleCloudForm;

import java.util.ArrayList;
import java.util.Objects;

import static util.waitmanager.WaitManager.waitForElementVisibility;

public class StepsOnGooglePricingCalculatorPage {
	
	private final WebDriver webDriver;
	private GoogleCloudPricingCalculatorPage googleCloudPricingCalculatorPage;
	private static ArrayList<String> tabs;
	private final GoogleCloudForm googleCloudForm;
	
	public StepsOnGooglePricingCalculatorPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		webDriver.manage().window().maximize();
		this.googleCloudForm = new GoogleCloudForm.BuilderGoogleCloudForm()
			.withGPUNumber(Integer.parseInt(PropertyDataReader.getTestBusinessValues("gpuNumber")))
			.withGPUType(PropertyDataReader.getTestBusinessValues("gpuType"))
			.withLocalSSDNumber(Integer.parseInt(PropertyDataReader.getTestBusinessValues("localSSDNumber")))
			.withDatacenterLocation(PropertyDataReader.getTestBusinessValues("datacenterLocation"))
			.withCommittedUsageNumber(Integer.parseInt(PropertyDataReader.getTestBusinessValues("committedUsageNumber")))
			.build();
	}
	
	
	public StepsOnGooglePricingCalculatorPage openPricingCalculator() {
		GoogleCloudResultPage googleCloudResultPage = new GoogleCloudResultPage(webDriver);
		waitForElementVisibility(googleCloudResultPage.searchResult);
		googleCloudResultPage.searchResult.click();
		return this;
	}
	
	public StepsOnGooglePricingCalculatorPage switchToCalculatorFrame() {
		googleCloudPricingCalculatorPage = new GoogleCloudPricingCalculatorPage(webDriver);
		webDriver.switchTo().frame(googleCloudPricingCalculatorPage.calculatorIFrame)
			.switchTo().frame(googleCloudPricingCalculatorPage.internalCalculatorFrame);
		return this;
	}
	
	public StepsOnGooglePricingCalculatorPage inputNumberOfInstances(String number) {
		googleCloudPricingCalculatorPage.inputNumberOfInstances.click();
		googleCloudPricingCalculatorPage.inputNumberOfInstances.sendKeys(number);
		return this;
	}
	
	public StepsOnGooglePricingCalculatorPage selectSeries() {
		googleCloudPricingCalculatorPage.seriesSelectField.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.seriesSelectedOptions);
		googleCloudPricingCalculatorPage.seriesSelectedOptions.click();
		return this;
	}
	
	public StepsOnGooglePricingCalculatorPage selectMachineType() {
		waitForElementVisibility(googleCloudPricingCalculatorPage.machineTypeSelectedField);
		googleCloudPricingCalculatorPage.machineTypeSelectedField.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.machineTypeSelect);
		googleCloudPricingCalculatorPage.machineTypeSelect.click();
		return this;
	}
	
	public StepsOnGooglePricingCalculatorPage addGPU() {
		waitForElementVisibility(googleCloudPricingCalculatorPage.addGPUCheckbox);
		googleCloudPricingCalculatorPage.addGPUCheckbox.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.numberOfGPUDropdown);
		googleCloudPricingCalculatorPage.numberOfGPUDropdown.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.chooseNumberOfGPUOption);
		WebElement numberOfGPU = null;
		try {
			numberOfGPU = webDriver
				.findElement(By.cssSelector(String
					.format(googleCloudPricingCalculatorPage.getNumberOfGPULocator(), googleCloudForm.getGpuNumber())));
		} catch (GPUNumberException e) {
			e.printStackTrace();
		}
		waitForElementVisibility(numberOfGPU);
		Objects.requireNonNull(numberOfGPU).click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.gPUTypeDropdown);
		googleCloudPricingCalculatorPage.gPUTypeDropdown.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.gPUTypeOption);
		WebElement gpuType = webDriver
			.findElement(By.cssSelector(String
				.format(googleCloudPricingCalculatorPage.getChooseGPUTypeLocator(), googleCloudForm.getGpuTypeValue())));
		waitForElementVisibility(gpuType);
		gpuType.click();
		return this;
	}
	
	public StepsOnGooglePricingCalculatorPage addSSD() {
		googleCloudPricingCalculatorPage.localSSDDropdown.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.localSSDNumberOption);
		WebElement localSSDNumber = null;
		try {
			localSSDNumber = webDriver
				.findElement(By.cssSelector(String
					.format(googleCloudPricingCalculatorPage.getLocalSSDNumberLocator(), googleCloudForm.getLocalSSDNumber())));
		} catch (LocalSSDNumberException e) {
			e.printStackTrace();
		}
		waitForElementVisibility(localSSDNumber);
		Objects.requireNonNull(localSSDNumber).click();
		return this;
	}
	
	public StepsOnGooglePricingCalculatorPage selectDatacenterLocation() {
		googleCloudPricingCalculatorPage.datacenterLocationDropdown.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.datacenterLocationOption);
		WebElement datacenterLocation = webDriver
			.findElement(By.cssSelector(String
				.format(googleCloudPricingCalculatorPage.getDatacenterLocationChoiceLocator(), googleCloudForm.getDatacenterLocationValue())));
		waitForElementVisibility(datacenterLocation);
		datacenterLocation.click();
		return this;
	}
	
	public StepsOnGooglePricingCalculatorPage selectCommittedUsage() {
		googleCloudPricingCalculatorPage.committedUsageDropdown.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.committedUsageOption);
		WebElement committedUsageChoice = null;
		try {
			committedUsageChoice = webDriver
				.findElement(By.cssSelector(String
					.format(googleCloudPricingCalculatorPage.getCommittedUsageChoiceLocator(), googleCloudForm.getCommittedUsageNumber())));
		} catch (CommittedUsageException e) {
			e.printStackTrace();
		}
		waitForElementVisibility(committedUsageChoice);
		Objects.requireNonNull(committedUsageChoice).click();
		return this;
	}
	
	public void pressAddToEstimate() {
		googleCloudPricingCalculatorPage.addToEstimateButton.click();
	}
	
	public void inputMailAddressIntoEstimateForm() {
		for (String tab : tabs) {
			System.out.println(tab);
		}
		webDriver.switchTo().window(tabs.get(0));
		webDriver.switchTo().frame(googleCloudPricingCalculatorPage.calculatorIFrame)
			.switchTo().frame(googleCloudPricingCalculatorPage.internalCalculatorFrame);
		waitForElementVisibility(googleCloudPricingCalculatorPage.eMailEstimateForm);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		googleCloudPricingCalculatorPage.eMailEstimateForm.click();
		googleCloudPricingCalculatorPage.eMailInputField
			.sendKeys(StepsOnTenMinutesMailPage.getEMailAddressFromPage());
		googleCloudPricingCalculatorPage.sendEMail.click();
	}
	
	public String getTotalEstimatedCostString() {
		return googleCloudPricingCalculatorPage.totalCostFieldInComputeEngineForm.getText();
	}
	
	public String getTotalEstimateCost() {
		return StringUtils.getNumberFromString(getTotalEstimatedCostString());
	}
	
	public void quitDriver() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}
}
