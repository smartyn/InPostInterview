package tests;
import helpers.ApiHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import models.InPostAPI;
import models.ParcelLocker;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InPostMainPage;
import pages.ParcelTrackingPage;
import testdata.DataProviderClass;

import java.util.List;

import static io.restassured.RestAssured.given;

@Slf4j
public class ParcelTrackingTests extends TestBase {

    InPostAPI inPostAPI = new InPostAPI();
    ApiHelper apiHelper = new ApiHelper();
    private static final List<String> cities = List.of("Lubin", "Rawicz", "Szczecin");

    @Test(groups = "GUI",
            dataProvider = "parcelData", dataProviderClass = DataProviderClass.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Find parcel number and get parcel status")
    public void tc01_SearchParcelAndCheckStatus(String parcelNumber, String parcelStatus) {
        SoftAssert softAssert = new SoftAssert();
        at(InPostMainPage.class)
                .clickOnAcceptButton()
                .searchParcelNumber(parcelNumber)
                .clickOnFindButton();
        softAssert.assertEquals(at(ParcelTrackingPage.class).getParcelStatus(), parcelStatus);
        softAssert.assertAll();
    }
    @Test(groups = "API")
    @Description("Parcel lockers search for a city with save the data to the file")
    @Severity(SeverityLevel.NORMAL)
    public void tc02_SearchParcelLockersInCities() {
        cities.parallelStream().forEach(city -> {
            RequestSpecification request =
                    given().spec(inPostAPI.baseReq()
                            .queryParam("city", city));
            Response res = request
                    .when()
                    .get()
                    .then()
                    .spec(inPostAPI.responseSpecification())
                    .extract().response();
            List<ParcelLocker> allLockers = res.jsonPath().getList("items", ParcelLocker.class);
            apiHelper.saveParcelLockerToFile(city, allLockers);
            Assert.assertNotNull(allLockers, "Items should not be null");
            Assert.assertFalse(allLockers.isEmpty(), "At least one parcel locker is desired");
            allLockers.stream().peek(parcelLocker -> {
                Assert.assertFalse(parcelLocker.getAddressDetails().getPostCode().isEmpty(), "Post code should not be empty");
                Assert.assertNotNull(parcelLocker.getAddressDetails().getPostCode(), "Post code should not be null");
            });
        });
    }

}
