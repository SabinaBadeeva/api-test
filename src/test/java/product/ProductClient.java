package product;
import basetest.Constants;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class ProductClient {
    public static ValidatableResponse getProductContent(){
            return RestAssured.given()
                .when()
                .get(Constants.API_FOOD)
                .then()
                    .log().all();
    }

    public static ValidatableResponse postProduct(Product product) {
        return RestAssured.given()
                .body(product)
                .when()
                .post(Constants.API_FOOD)
                .then()
                .log().all();
    }

    public static ValidatableResponse resetProduct() {
        return RestAssured.given()
                .when()
                .post(Constants.RESET)
                .then()
                .log().all();
    }

    public static String getJSessionId() {
        String jsessionid = given()
                .when()
                .get(Constants.API_FOOD)
                .then()
                .extract()
                .cookie("JSESSIONID");
        return jsessionid;
    }

    public static Product getLastProduct() {
        Product[] productList = given()
                .when()
                .get(Constants.API_FOOD)
                .as(Product[].class);
        return productList[productList.length - 1];
    }
}
