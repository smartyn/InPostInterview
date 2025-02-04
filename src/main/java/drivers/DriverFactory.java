package drivers;

import helpers.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public BrowserConfig create(String browser) {
        return new BrowserConfig(browser);
    }

    public static WebDriver getDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", Configuration.getChrome());
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", Configuration.getFirefox());
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "pathForEdgeDriver");
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }

}
