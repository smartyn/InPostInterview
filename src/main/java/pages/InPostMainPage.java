package pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Slf4j
public class InPostMainPage extends BasePage {

    public InPostMainPage(WebDriver driver) {
        super(driver);
    }

    By acceptCookiesButton = By.id("onetrust-accept-btn-handler");
    By parcelNumberInput = By.name("number");

    @Step("Click on Accept button")
    public InPostMainPage clickOnAcceptButton() {
        waitAndFindElement(acceptCookiesButton).click();
        return this;
    }

    @Step("Enter parcel number - {0}")
    public InPostMainPage searchParcelNumber(String parcelNumber) {
        waitAndFindElement(parcelNumberInput).sendKeys(parcelNumber);
        log.info("Parcel number: " + parcelNumber);
        return this;
    }

    @Step("Click on Find button")
    public InPostMainPage clickOnFindButton() {
        getFindButton().click();
        return this;
    }

    private WebElement getFindButton() {
        List<WebElement> buttons = driver.findElements(By.className("btn--primary"));
        return buttons
                .stream()
                .filter(b -> b.getAttribute("innerText").equalsIgnoreCase("Find") ||
                        b.getAttribute("innerText").equalsIgnoreCase("Znajdź"))
                .findFirst().get();
    }
}
