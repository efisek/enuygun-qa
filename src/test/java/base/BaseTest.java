package base;

import browser.BrowserFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import config.Config;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.FlightReservationPage;
import pages.FlightTicketSearchPage;
import pages.HomePage;
import pages.PaymentPage;
import utils.DriverUtility;
import utils.ReportManager;

import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected HomePage homePage;
    protected FlightTicketSearchPage flightTicketSearchPage;
    protected FlightReservationPage flightReservationPage;
    protected PaymentPage paymentPage;

    @BeforeSuite
    public void beforeSuite() {
        extent = ReportManager.createInstance("ExtentReport.html");
    }

    @BeforeMethod
    public void setUp(Method method) {
        driver = BrowserFactory.getBrowserSetup(Config.getProperty("browser")).setupDriver();
        driver.manage().window().maximize();
        driver.get(Config.getProperty("baseUrl"));

        test = extent.createTest(method.getName(),method.getAnnotation(Test.class).description());

        homePage = new HomePage(driver);
        flightTicketSearchPage = new FlightTicketSearchPage(driver);
        flightReservationPage = new FlightReservationPage(driver);
        paymentPage = new PaymentPage(driver);

    }

    @AfterMethod
    public void afterMethod (ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel("Test Failed: " + result.getName(), ExtentColor.RED));
            captureScreenshotOnFailure(result);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel("Test Passed " + result.getName(),ExtentColor.GREEN));
        } else {
            test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        }

        tearDown();
    }

    @AfterSuite
    public void afterSuite(){
        extent.flush();
    }


    //helper methods
    public void captureScreenshotOnFailure(ITestResult result) {
        if (!result.isSuccess()) {
            DriverUtility.takeScreenshot(driver, result.getName());
        }
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
