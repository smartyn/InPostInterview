package testdata;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "parcelData")
    public Object[][] addParcelData() {
        return new Object[][] {
                { "520113014230722029585646", "Delivered" },
                { "520107010449991105638120", "Passed for delivery" },
                { "523000016696115042036670", "Label nullified" },
                { "520000011395200025754311", "Delivered" }
        };
    }
}
