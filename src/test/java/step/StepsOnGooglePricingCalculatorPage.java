package step;

import exception.CommittedUsageException;
import exception.GPUNumberException;
import exception.LocalSSDNumberException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.googlecloud.GoogleCloudPricingCalculatorPage;
import page.googlecloud.GoogleCloudResultPage;
import page.tenminutesmail.TenMinutesMailPage;
import property.PropertyValueManager;
import util.StringUtils;
import valueobject.GoogleCloudForm;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static util.waitmanager.WaitManager.waitForElementVisibility;

public class StepsOnGooglePricingCalculatorPage {
	
	private final WebDriver webDriver;
	private GoogleCloudPricingCalculatorPage googleCloudPricingCalculatorPage;
//	private TenMinutesMailPage tenMinutesMailPage;
	private static ArrayList<String> tabs;
//	private String eMailAddress;
	private final GoogleCloudForm googleCloudForm;
	
	public StepsOnGooglePricingCalculatorPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		webDriver.manage().window().maximize();
//		eMailAddress = "";
		this.googleCloudForm = new GoogleCloudForm.BuilderGoogleCloudForm()
			.withGPUNumber(Integer.parseInt(PropertyValueManager.getTestData("gpuNumber")))
			.withGPUType(PropertyValueManager.getTestData("gpuType"))
			.withLocalSSDNumber(Integer.parseInt(PropertyValueManager.getTestData("localSSDNumber")))
			.withDatacenterLocation(PropertyValueManager.getTestData("datacenterLocation"))
			.withCommittedUsageNumber(Integer.parseInt(PropertyValueManager.getTestData("committedUsageNumber")))
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
	
	public StepsOnGooglePricingCalculatorPage addTenMinutesEMailTab() {
		((JavascriptExecutor) webDriver).executeScript("window.open('https://10minutemail.com','_blank')");
		tabs = new ArrayList<>(webDriver.getWindowHandles());
		return this;
	}
	
//	public StepsOnGooglePricingCalculatorPage getMailAddressFromTenMinutesMailSite() {
//		webDriver.switchTo().window(tabs.get(1));
//		eMailAddress = StringUtils.getEMailAddressAsString(te);
//		return this;
//	}
	
	public void inputMailAddressIntoEstimateForm() {
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
		googleCloudPricingCalculatorPage.eMailInputField.sendKeys();
		googleCloudPricingCalculatorPage.sendEMail.click();
	}
	
//	public void getMailOnTenMinutesMailBox() {
//		webDriver.switchTo().window(tabs.get(1));
//		tenMinutesMailPage.inboxCount = new WebDriverWait(webDriver, 60)
//			.until(ExpectedConditions.visibilityOf(tenMinutesMailPage.inboxCount));
//		tenMinutesMailPage.inboxCount.click();
//	}
	
//	public String getTextFromMail() {
//		return tenMinutesMailPage.estimateCosInMail.getText();
//	}
	
	public String getTotalEstimatedCostString() {
		return googleCloudPricingCalculatorPage.totalCostFieldInComputeEngineForm.getText();
	}
	
	public String getTotalEstimateCost() {
		return StringUtils.getNumberFromString(getTotalEstimatedCostString());
	}
	
//	public String getEstimateCostFromMail() {
//		return StringUtils.getNumberFromString(getTextFromMail());
//	}
	
	public void quitDriver() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}
	
//	private String getTenMinutesEMailAddressAsString() {
//		tenMinutesMailPage = new TenMinutesMailPage(webDriver);
//		tenMinutesMailPage.eMailAddress.click();
//		String resultMailAddress = "";
//		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//		Transferable contents = clipboard.getContents(null);
//		boolean hasStringText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
//		if (hasStringText) {
//			try {
//				resultMailAddress = (String) contents.getTransferData(DataFlavor.stringFlavor);
//			} catch (UnsupportedFlavorException | IOException ex) {
//				System.out.println(ex);
//				ex.printStackTrace();
//			}
//		}
//		return resultMailAddress;
//	}
}
