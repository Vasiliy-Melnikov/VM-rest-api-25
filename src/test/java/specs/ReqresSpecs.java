package specs;

import helpers.CustomAllureListener;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;

public class ReqresSpecs {

    protected static final String API_KEY = "reqres_4d65289728eb4794916f54583a05546f";

    public static final RequestSpecification reqresRequestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setBasePath("/api")
            .setContentType(ContentType.JSON)
            .addHeader("x-api-key", API_KEY)
            .log(ALL)
            .addFilter(CustomAllureListener.withCustomTemplates())
            .build();

    public static final ResponseSpecification status200 = new ResponseSpecBuilder()
            .expectStatusCode(200).log(ALL).build();

    public static final ResponseSpecification status201 = new ResponseSpecBuilder()
            .expectStatusCode(201).log(ALL).build();

    public static final ResponseSpecification status204 = new ResponseSpecBuilder()
            .expectStatusCode(204).log(ALL).build();

    public static final ResponseSpecification status400 = new ResponseSpecBuilder()
            .expectStatusCode(400).log(ALL).build();
}