package api.tests.cart;

import api.common.ApiClient;
import api.models.request.AddToCartRequest;
import api.models.response.MessageDto;
import api.models.response.ProductDto;
import api.models.response.ShoppingCartResponse;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import api.tests.CommonTest;

import java.math.BigDecimal;
import java.util.List;

import static api.common.ApiClient.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends CommonTest {
    @Test
    @Order(1)
    void postCartTest() {
        var response = commonPost(
                accessToken,
                201,
                new AddToCartRequest(1, 2),
                config.getConfigParameter("CART_PATH")
        ).extract().as(MessageDto.class);
        assertThat(response).usingRecursiveComparison().isEqualTo(new MessageDto("Product added to cart successfully"));
    }

    @Test
    @Order(2)
    void getCartTest() {
        var shoppingCart = new ShoppingCartResponse(
                List.of(
                        new ShoppingCartResponse.ProductInCartDto(
                                1,
                                "HP Pavilion Laptop",
                                "Electronics",
                                new BigDecimal("10.99"),
                                new BigDecimal("10.0"),
                                24
                        )
                ),
                new BigDecimal("274.75"),
                new BigDecimal("27.48")
        );
        var response = commonGet(accessToken, 200, config.getConfigParameter("CART_PATH")).extract().as(ShoppingCartResponse.class);
        assertThat(response).usingRecursiveComparison().ignoringFields("total_discount", "total_price", "cart.quantity").isEqualTo(shoppingCart);
    }

    @Test
    @Order(3)
    void deleteCartTest() {
        var response = commonDelete(accessToken, 200, config.getConfigParameter("CART_BY_ID_PATH"), 1).extract().as(MessageDto.class);
        assertThat(response).usingRecursiveComparison().isEqualTo(new MessageDto("Product removed from cart"));
    }
}
