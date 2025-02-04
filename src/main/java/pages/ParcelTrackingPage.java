package pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Slf4j
public class ParcelTrackingPage extends BasePage {

    public ParcelTrackingPage(WebDriver driver) {
        super(driver);
    }
    By messageBox = By.className("message-box");

    @Step("Get parcel status - {0}")
    public String getParcelStatus() {
        String parcelStatus = waitAndFindElement(messageBox).getText().trim();
        log.info("Parcel status is: " + parcelStatus);
        return parcelStatus.split("\n")[0];
    }


}
