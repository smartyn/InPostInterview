package models;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class InPostAPI {

    public RequestSpecification baseReq() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.inpost-group.com/geowidget/v1/points")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        return requestSpecification;
    }

    public ResponseSpecification responseSpecification() {
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        return responseSpec;
    }
}
