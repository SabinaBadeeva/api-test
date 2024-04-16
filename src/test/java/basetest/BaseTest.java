package basetest;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void init() {
        BasApi.installSpecification(BasApi.requestSpecification(Constants.BAS_URL));

    }

    public static Object[][] testExoticProduct() {
        return new Object[][]{
                {"Арбуз", "FRUIT", true},
                {"Пепино", "VEGETABLE", true},
        };
    }

    public static Object[][] testNotExoticProduct() {
        return new Object[][]{
                {"Морковь", "FRUIT", true},
                {"Слива", "VEGETABLE", true},
        };
    }
}
