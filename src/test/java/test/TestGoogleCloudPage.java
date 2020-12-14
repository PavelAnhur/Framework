package test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import step.Steps;

import static webdriver.WebDriverSetup.getDriver;

@Listeners(test.TestListener.class)
public class TestGoogleCloudPage {

	private Steps steps;

	@BeforeTest
	public void setupClass() {
		steps = new Steps(getDriver());
		steps.openGoogleCloudPage();
	}

	@Test
	public void googleCloudTest() {
		steps.openPricingCalculator()
				.switchToCalculatorFrame()
				.inputNumberOfInstances("4")
				.selectSeries()
				.selectMachineType()
				.addGPU()
				.addSSD()
				.selectDatacenterLocation()
				.selectCommittedUsage()
				.pressAddToEstimate();
		String totalCostEstimateCost = steps.getTotalEstimateCost();
		steps.addTenMinutesEMailTab()
				.getMailAddressFromTenMinutesMailSite()
				.inputMailAddressIntoEstimateForm()
				.getMailOnTenMinutesMailBox();
		String estimateCostFromMail = steps.getEstimateCostFromMail();

		Assert.assertEquals(totalCostEstimateCost, estimateCostFromMail);
//		Assert.fail();
	}

	@AfterTest
	public void teardown() {
		steps.quitDriver();
		steps = null;
	}
}
