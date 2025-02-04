package tests;

import drivers.BrowserConfig;
import drivers.BrowserLauncher;
import drivers.DriverFactory;
import helpers.Configuration;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;
import pages.InPostMainPage;
import pages.ParcelTrackingPage;

import java.io.File;
import java.io.IOException;

@Slf4j
public class TestBase {

    private String browser;
    protected WebDriver driver;
    private Configuration config;


    @BeforeMethod
    public void setupTest() {
        try {
            config = new Configuration();
            String testType = config.getTestType();
            driver = initWebDriver();
            driver.manage().window().maximize();
            driver.navigate().to(Configuration.getConfigurationValueFromRegionConfigurationFile("appUrl"));
        } catch (Exception e) {
            System.out.println(e);
            tearDown();
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        log.debug("Close webdriver");
        if (driver != null) {
            driver.close();
        }
    }

    @AfterMethod
    protected void screenShotIfFail(ITestResult result) throws IOException {
        /*log.info(String.format("Test execution status: %s", converTestResultStatusCodeToText(result.getStatus())));
        log.info("-----END TEST-----");*/
        if (!result.isSuccess()) {
            takeScreenshot(result.getMethod().getMethodName());
        }
    }
    private WebDriver initWebDriver() {
        log.debug("Initializing new WebDriver");
        browser = Configuration.getBrowser();
        DriverFactory driverFactory = new DriverFactory();
        BrowserConfig browserConfig = driverFactory.create(browser);
        BrowserLauncher browserLauncher = new BrowserLauncher(browserConfig);
        return browserLauncher.initializeWebDriver();
    }

    @Attachment(value = "{name}", type = "image/png")
    private byte[] takeScreenshot(String name) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @SneakyThrows
    public <T extends BasePage> T at(Class<T> page) {
        return page.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }

    private String converTestResultStatusCodeToText(int statusCode) {
        String statusText;
        switch (statusCode) {
            case 1:
                statusText = "PASS";
                break;
            case 2:
                statusText = "FAILED";
                break;
            case 3:
                statusText = "SKIP";
                break;
            default:
                statusText = String.format("Unhandled status code: %s", statusCode);
                break;
        } return statusText;
    }
}
