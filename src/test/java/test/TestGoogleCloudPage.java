package test;

import org.testng.Assert;
import org.testng.annotations.*;
import property.PropertyDataReader;
import step.StepsOnGoogleCloudPage;
import step.StepsOnGooglePricingCalculatorPage;
import step.StepsOnTenMinutesMailPage;
import util.listener.TestListener;

import static util.webdriver.WebDriverSetup.getDriver;

@Listeners(TestListener.class)
public class TestGoogleCloudPage {
	
	private StepsOnGooglePricingCalculatorPage stepsOnGooglePricingCalculatorPage;
	private StepsOnTenMinutesMailPage stepsOnTenMinutesMailPage;
	private String totalCostEstimateCost;
	
	public TestGoogleCloudPage() {
		this.totalCostEstimateCost = "";
	}
	
	@BeforeClass
	public void setupClass() {
		new StepsOnGoogleCloudPage(getDriver())
			.openGoogleCloudPage()
			.inputSearchTerm();
	}
	
	@Test
	public void googleCloudPageTest() {
		stepsOnGooglePricingCalculatorPage = new StepsOnGooglePricingCalculatorPage(getDriver());
		stepsOnGooglePricingCalculatorPage
			.openPricingCalculator()
			.switchToCalculatorFrame()
			.inputNumberOfInstances(PropertyDataReader.getTestBusinessValues("numberOfInstances"))
			.selectSeries()
			.selectMachineType()
			.addGPU()
			.addSSD()
			.selectDatacenterLocation()
			.selectCommittedUsage()
			.pressAddToEstimate();
		totalCostEstimateCost = stepsOnGooglePricingCalculatorPage.getTotalEstimateCost();
	}
	
	@Test
	public void tenMinutesMailPageTest() {
		stepsOnTenMinutesMailPage = new StepsOnTenMinutesMailPage(getDriver());
		stepsOnTenMinutesMailPage
			.addTenMinutesEMailTab()
			.getMailAddressFromTenMinutesMailSite();
		stepsOnGooglePricingCalculatorPage
			.inputMailAddressIntoEstimateForm();
		stepsOnTenMinutesMailPage
			.getMailOnTenMinutesMailBox();
		String estimateCostFromMail = stepsOnTenMinutesMailPage.getEstimateCostFromMail();
		
		Assert.assertEquals(totalCostEstimateCost, estimateCostFromMail);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		stepsOnGooglePricingCalculatorPage.quitDriver();
		stepsOnGooglePricingCalculatorPage = null;
	}
}
