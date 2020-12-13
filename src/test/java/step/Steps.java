package step;

import excpetion.CommittedUsageException;
import excpetion.GPUNumberException;
import excpetion.LocalSSDNumberException;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.googlecloud.GoogleCloudPage;
import page.googlecloud.GoogleCloudPricingCalculatorPage;
import page.googlecloud.GoogleCloudResultPage;
import page.tenminutesmail.TenMinutesMailPage;
import valueobject.GoogleCloudForm;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static waitmanager.WaitManager.waitForElementVisibility;

public class Steps {

	private final WebDriver webDriver;
	private GoogleCloudPage googleCloudPage;
	private GoogleCloudPricingCalculatorPage googleCloudPricingCalculatorPage;
	private TenMinutesMailPage tenMinutesMailPage;
	private ArrayList<String> tabs;
	private String eMailAddress;
	private final GoogleCloudForm googleCloudForm;

	public Steps(WebDriver webDriver) {
		this.webDriver = webDriver;
		webDriver.manage().window().maximize();
		eMailAddress = "";
		this.googleCloudForm = new GoogleCloudForm(1, "NVIDIA_TESLA_P4", 2
				, "europe-west3", 1);
	}

	public void openGoogleCloudPage() {
		googleCloudPage = new GoogleCloudPage(webDriver);
		webDriver.get("https://cloud.google.com/");
	}

	public Steps openPricingCalculator() {
		googleCloudPage.searchField.sendKeys("Google Cloud Platform Pricing Calculator");
		googleCloudPage.searchField.submit();
		GoogleCloudResultPage googleCloudResultPage = new GoogleCloudResultPage(webDriver);
		waitForElementVisibility(googleCloudResultPage.searchResult);
		googleCloudResultPage.searchResult.click();
		return this;
	}

	public Steps switchToCalculatorFrame() {
		googleCloudPricingCalculatorPage = new GoogleCloudPricingCalculatorPage(webDriver);
		webDriver.switchTo().frame(googleCloudPricingCalculatorPage.calculatorIFrame)
				.switchTo().frame(googleCloudPricingCalculatorPage.internalCalculatorFrame);
		return this;
	}

	public Steps inputNumberOfInstances(String number) {
		googleCloudPricingCalculatorPage.inputNumberOfInstances.click();
		googleCloudPricingCalculatorPage.inputNumberOfInstances.sendKeys(number);
		return this;
	}

	public Steps selectSeries() {
		googleCloudPricingCalculatorPage.seriesSelectField.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.seriesSelectedOptions);
		googleCloudPricingCalculatorPage.seriesSelectedOptions.click();
		return this;
	}

	public Steps selectMachineType() {
		waitForElementVisibility(googleCloudPricingCalculatorPage.machineTypeSelectedField);
		googleCloudPricingCalculatorPage.machineTypeSelectedField.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.machineTypeSelect);
		googleCloudPricingCalculatorPage.machineTypeSelect.click();
		return this;
	}

	public Steps addGPU() {
		waitForElementVisibility(googleCloudPricingCalculatorPage.addGPUCheckbox);
		googleCloudPricingCalculatorPage.addGPUCheckbox.click();
		googleCloudPricingCalculatorPage.numberOfGPUDropdown.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.chooseNumberOfGPUOption);
		WebElement numberOfGPU = null;
		try {
			numberOfGPU = webDriver
					.findElement(By.cssSelector(String
							.format(googleCloudPricingCalculatorPage.numberOfGPULocator, googleCloudForm.getGpuNumber())));
		} catch (GPUNumberException e) {
			e.printStackTrace();
		}
		waitForElementVisibility(numberOfGPU);
		Objects.requireNonNull(numberOfGPU).click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.gPUTypeDropdown);
		googleCloudPricingCalculatorPage.gPUTypeDropdown.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.gPUTypeOption);
		WebElement GPUType = webDriver
				.findElement(By.cssSelector(String
						.format(googleCloudPricingCalculatorPage.chooseGPUTypeLocator, googleCloudForm.getGpuTypeValue())));
		waitForElementVisibility(GPUType);
		GPUType.click();
		return this;
	}

	public Steps addSSD() {
		googleCloudPricingCalculatorPage.localSSDDropdown.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.localSSDNumberOption);
		WebElement localSSDNumber = null;
		try {
			localSSDNumber = webDriver
					.findElement(By.cssSelector(String
							.format(googleCloudPricingCalculatorPage.localSSDNumberLocator, googleCloudForm.getLocalSSDNumber())));
		} catch (LocalSSDNumberException e) {
			e.printStackTrace();
		}
		waitForElementVisibility(localSSDNumber);
		Objects.requireNonNull(localSSDNumber).click();
		return this;
	}

	public Steps selectDatacenterLocation() {
		googleCloudPricingCalculatorPage.datacenterLocationDropdown.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.datacenterLocationOption);
		WebElement datacenterLocation = webDriver
				.findElement(By.cssSelector(String
						.format(googleCloudPricingCalculatorPage.datacenterLocationChoiceLocator, googleCloudForm.getDatacenterLocationValue())));
		waitForElementVisibility(datacenterLocation);
		datacenterLocation.click();
		return this;
	}

	public Steps selectCommittedUsage() {
		googleCloudPricingCalculatorPage.committedUsageDropdown.click();
		waitForElementVisibility(googleCloudPricingCalculatorPage.committedUsageOption);
		WebElement committedUsageChoice = null;
		try {
			committedUsageChoice = webDriver
					.findElement(By.cssSelector(String
							.format(googleCloudPricingCalculatorPage.committedUsageChoiceLocator, googleCloudForm.getCommittedUsageNumber())));
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

	public Steps addTenMinutesEMailTab() {
		((JavascriptExecutor) webDriver).executeScript("window.open('https://10minutemail.com','_blank')");
		tabs = new ArrayList<>(webDriver.getWindowHandles());
		return this;
	}

	public Steps getMailAddressFromTenMinutesMailSite() {
		webDriver.switchTo().window(tabs.get(1));
		eMailAddress = getTenMinutesEMailAddressAsString();
		return this;
	}

	public Steps inputMailAddressIntoEstimateForm() {
		webDriver.switchTo().window(tabs.get(0));
		webDriver.switchTo().frame(googleCloudPricingCalculatorPage.calculatorIFrame)
				.switchTo().frame(googleCloudPricingCalculatorPage.internalCalculatorFrame);
		googleCloudPricingCalculatorPage.eMailEstimateForm.click();
		googleCloudPricingCalculatorPage.eMailInputField.sendKeys(eMailAddress);
		googleCloudPricingCalculatorPage.sendEMail.click();
		return this;
	}

	public void getMailOnTenMinutesMailBox() {
		webDriver.switchTo().window(tabs.get(1));
		tenMinutesMailPage.inboxCount = new WebDriverWait(webDriver, 20)
				.until(ExpectedConditions.visibilityOf(tenMinutesMailPage.inboxCount));
		tenMinutesMailPage.inboxCount.click();
	}

	public String getTextFromMail() {
		return tenMinutesMailPage.estimateCosInMail.getText();
	}

	public String getTotalEstimatedCostString() {
		return googleCloudPricingCalculatorPage.totalCostFieldInComputeEngineForm.getText();
	}

	public String getTotalEstimateCost() {
		return getCostFromString(getTotalEstimatedCostString());
	}

	public String getEstimateCostFromMail() {
		return getCostFromString(getTextFromMail());
	}

	public void quitDriver() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

	private String getTenMinutesEMailAddressAsString() {
		tenMinutesMailPage = new TenMinutesMailPage(webDriver);
		tenMinutesMailPage.eMailAddress.click();
		String resultMailAddress = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		boolean hasStringText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		if (hasStringText) {
			try {
				resultMailAddress = (String) contents.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException | IOException ex) {
				System.out.println(ex);
				ex.printStackTrace();
			}
		}
		return resultMailAddress;
	}

	private String getCostFromString(String string) {
		String[] wordsFromString = string.split(" ");
		for (String word : wordsFromString) {
			if (NumberUtils.isParsable(String.valueOf(word.charAt(0)))) {
				return word;
			}
		}
		return null;
	}
}
