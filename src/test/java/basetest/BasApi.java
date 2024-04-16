package basetest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import product.ProductClient;

public class BasApi {

    public static RequestSpecification requestSpecification(String BaseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(BaseUri)
                .setContentType(ContentType.JSON)
                .setSessionId(ProductClient.getJSessionId())
                .build();
    }

    public static void installSpecification(RequestSpecification requestSpecification) {
        RestAssured.requestSpecification = requestSpecification;
    }
}

