package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Slf4j
public class BasePage {

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void waitForElementToBeVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected WebElement waitAndFindElement(By by) {
        waitForElementToBeVisible(by);
        return driver.findElement(by);
    }


}
