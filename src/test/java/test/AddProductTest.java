package test;

import basetest.BaseTest;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import product.Product;
import product.ProductClient;

import java.util.ArrayList;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddProductTest extends BaseTest {

    @DisplayName("Добавить и удалить новые продукты по типу Фрукт и Овощ, exotic = false")
    @Description("успешный запрос возвращает 200")
    @ParameterizedTest
    @MethodSource("testNotExoticProduct")
    public void addProductTest(String name, String type, boolean exotic) {
        Product newProduct = new Product(name, type, exotic);
        // Сделать запрос на содержание таблицы
        ValidatableResponse validatableResponse = ProductClient.getProductContent();
        //Проверить, что в тело ответа возвращается список
        ArrayList actualProductList = validatableResponse.extract().path("name");
        boolean list = !actualProductList.isEmpty();
        assertTrue(list, "Список пустой");
        //Добавить новые продукты
        ValidatableResponse response = ProductClient.postProduct(newProduct);
        //Проверить статус код
        int statusCode = response.extract().statusCode();
        assertEquals(SC_OK, statusCode, "Статус код не соответсвует 200");
        //Проверить, что продукты добавились
        assertEquals(newProduct, ProductClient.getLastProduct(), "Новые продукты не добавились");
        //Удалить продукты
        ValidatableResponse responseDel = ProductClient.resetProduct();
        //Проверить статус код
        int statusCodeDel = responseDel.extract().statusCode();
        assertEquals(SC_OK, statusCodeDel, "Статус код не соответсвует 200");
        //Проверить, что продукты удалились
        Assertions.assertNotEquals(newProduct, ProductClient.getLastProduct(), "Продукты не удалились");
    }

    @DisplayName("Добавить и удалить новые продукты по типу Фрукт и Овощ, exotic = false")
    @Description("успешный запрос возвращает 200")
    @ParameterizedTest
    @MethodSource("testExoticProduct")
    public void addNotExoticProduct(String name, String type, boolean exotic) {
        Product newProduct = new Product(name, type, exotic);
        // Сделать запрос на содержание таблицы
        ValidatableResponse validatableResponse = ProductClient.getProductContent();
        //Проверить, что в тело ответа возвращается список
        ArrayList actualProductList = validatableResponse.extract().path("name");
        boolean list = !actualProductList.isEmpty();
        assertTrue(list, "Список пустой");
        //Добавить новые продукты
        ValidatableResponse response = ProductClient.postProduct(newProduct);
        //Проверить статус код
        int statusCode = response.extract().statusCode();
        assertEquals(SC_OK, statusCode, "Статус код не соответсвует 200");
        //Проверить, что продукты добавились
        assertEquals(newProduct, ProductClient.getLastProduct(), "Новые продукты не добавились");
        //Удалить продукты
        ValidatableResponse responseDel = ProductClient.resetProduct();
        //Проверить статус код
        int statusCodeDel = responseDel.extract().statusCode();
        assertEquals(SC_OK, statusCodeDel, "Статус код не соответсвует 200");
        //Проверить, что продукты удалились
        Assertions.assertNotEquals(newProduct, ProductClient.getLastProduct(), "Продукты не удалились");
    }


}
