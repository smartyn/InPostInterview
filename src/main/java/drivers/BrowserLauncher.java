package drivers;

import org.openqa.selenium.WebDriver;

public class BrowserLauncher {

    private BrowserConfig browserConfig;

    public BrowserLauncher(BrowserConfig browserConfig) {
        this.browserConfig = browserConfig;
    }

    public WebDriver initializeWebDriver() {
        return DriverFactory.getDriver(browserConfig.getBrowser());
    }
}
