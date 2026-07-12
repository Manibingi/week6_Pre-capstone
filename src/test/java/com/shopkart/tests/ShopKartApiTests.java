package com.shopkart.tests;
import com.shopkart.api.specs.ResponseSpecs;
import com.shopkart.data.OrderBuilder;
import com.shopkart.support.BaseApiTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
public class ShopKartApiTests extends BaseApiTest {

    @Test
    @Tag("api")
    void prod_search_matching_products() {
        productClient.searchProducts("bag")
                .then()
                .spec(ResponseSpecs.ok200())
                .body("size()", greaterThan(0))
                .body("[0].sku", equalTo("SKU-BAG"));
    }

    @Test
    @Tag("api")
    void cart_total_in_paise() {
        String token = loginAs("alice");
        int cartId = cartClient.createCart(token)
                .then()
                .spec(ResponseSpecs.created201())
                .extract()
                .path("cartId");

        cartClient.addItem(token, cartId, "SKU-BAG", 2)
                .then()
                .spec(ResponseSpecs.ok200())
                .body("totalPaise", equalTo(99800));
    }

    @Test
    @Tag("api")
    void checkout_place_order() {
        String token = loginAs("alice");
        int cartId = cartClient.createCart(token)
                .then()
                .spec(ResponseSpecs.created201())
                .extract()
                .path("cartId");
        cartClient.addItem(token, cartId, "SKU-BAG", 2);
        Map<String, Object> order = OrderBuilder.anOrder()
                .withCartId(cartId)
                .withAddress("Chennai, Tamilnadu")
                .build();

        int orderId = orderClient.placeOrder(token, order)
                .then()
                .log().all()
                .spec(ResponseSpecs.created201())
                .body("status", equalTo("PLACED"))
                .extract()
                .path("orderId");
        orderClient.getOrder(token, orderId)
                .then()
                .spec(ResponseSpecs.ok200())
                .body("status", equalTo("PLACED"))
                .body("totalPaise", equalTo(99800));
    }

    @Test
    @Tag("api")
    void another_cust_access_order() {

        String aliceToken = loginAs("alice");
        String bobToken = loginAs("bob");

        int cartId = cartClient.createCart(aliceToken)
                .then()
                .spec(ResponseSpecs.created201())
                .extract()
                .path("cartId");
        cartClient.addItem(aliceToken, cartId, "SKU-BAG", 1);
        Map<String, Object> order = OrderBuilder.anOrder()
                .withCartId(cartId)
                .build();

        int orderId = orderClient.placeOrder(aliceToken, order)
                .then()
                .spec(ResponseSpecs.created201())
                .extract()
                .path("orderId");

        orderClient.getOrder(bobToken, orderId)
                .then()
                .spec(ResponseSpecs.forbidden403());
    }

    @Test
    @Tag("api")
    void cancel_only_once() {

        String token = loginAs("alice");

        int cartId = cartClient.createCart(token)
                .then()
                .spec(ResponseSpecs.created201())
                .extract()
                .path("cartId");

        cartClient.addItem(token, cartId, "SKU-BAG", 1);
        Map<String, Object> order = OrderBuilder.anOrder()
                .withCartId(cartId)
                .build();
        int orderId = orderClient.placeOrder(token, order)
                .then()
                .spec(ResponseSpecs.created201())
                .extract()
                .path("orderId");
        orderClient.cancelOrder(token, orderId)
                .then()
                .spec(ResponseSpecs.ok200())
                .body("status", equalTo("CANCELLED"));
        orderClient.cancelOrder(token, orderId)
                .then()
                .spec(ResponseSpecs.conflict409());
    }

    @Test
    @Tag("api")
    void out_of_stock_409() {

        String token = loginAs("alice");
        int cartId = cartClient.createCart(token)
                .then()
                .spec(ResponseSpecs.created201())
                .extract()
                .path("cartId");
        cartClient.addItem(token, cartId, "SKU-CAP", 1)
                .then()
                .spec(ResponseSpecs.conflict409());
    }
}