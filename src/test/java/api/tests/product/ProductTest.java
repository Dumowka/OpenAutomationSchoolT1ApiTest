package api.tests.product;

import api.common.ApiClient;
import api.models.request.ProductRequestDto;
import api.models.response.ProductDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import api.tests.CommonTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static api.common.ApiClient.commonGet;
import static api.common.ApiClient.commonPost;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest extends CommonTest {
    @Test
    void getProductsTest() {
        commonGet(accessToken, 200, config.getConfigParameter("PRODUCTS_PATH"));
    }

    // Возвращает 405
    @Test
    void postProductTest() {
        commonPost(
                accessToken,
                201,
                new ProductRequestDto("name", "category", new BigDecimal("1.0"), new BigDecimal("1.0")),
                config.getConfigParameter("PRODUCTS_PATH")
        );
    }

    @Test
    void getProductTest() {
        var product = new ProductDto(1, "HP Pavilion Laptop", "Electronics", new BigDecimal("10.99"), new BigDecimal("10.0"));
        var response = commonGet(accessToken,200, config.getConfigParameter("PRODUCT_BY_ID_PATH"), 1)
                .extract().jsonPath().getList(".", ProductDto.class);
        assertThat(response.size()).isOne();
        assertThat(response).usingRecursiveFieldByFieldElementComparator().contains(product);
    }

    // Возвращает 405
    @Test
    void putProductTest() {
        var products = List.of(
                new ProductDto(1, "HP Pavilion Laptop", "Electronics", new BigDecimal("10.99"), new BigDecimal("10.0")),
                new ProductDto(2, "Samsung Galaxy Smartphone", "Electronics", new BigDecimal("15.99"), null),
                new ProductDto(3, "Adidas T-shirt", "Clothing", new BigDecimal("8.99"), new BigDecimal("2.5")),
                new ProductDto(4, "Levis Jeans", "Clothing", new BigDecimal("12.99"), new BigDecimal("15.0")),
                new ProductDto(5, "HP Pavilion Laptop", "Electronics", new BigDecimal("10.99"), new BigDecimal("10.0")),
                new ProductDto(6, "Samsung Galaxy Smartphone", "Electronics", new BigDecimal("15.99"), null),
                new ProductDto(7, "Adidas T-shirt", "Clothing", new BigDecimal("8.99"), new BigDecimal("2.5")),
                new ProductDto(8, "Levis Jeans", "Clothing", new BigDecimal("12.99"), new BigDecimal("15.0"))
        );

        var response = ApiClient.commonPut(
                accessToken,
                200,
                new ProductRequestDto("name", "category", new BigDecimal("1.0"), new BigDecimal("1.0")),
                config.getConfigParameter("PRODUCT_BY_ID_PATH"),
                1
        ).extract().jsonPath().getList(".", ProductDto.class);

        assertThat(response).usingRecursiveFieldByFieldElementComparator().isEqualTo(products);
    }

    // Возвращает 405
    @Test
    void deleteProductTest() {
        ApiClient.commonDelete(accessToken, 200, config.getConfigParameter("PRODUCT_BY_ID_PATH"), 1);
    }
}
