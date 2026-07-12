package com.shopkart.api;

import com.shopkart.api.specs.RequestSpecs;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class CartClient {

    private final RequestSpecification request;

    public CartClient(RequestSpecification request) {
        this.request = request;
    }

    public Response createCart(String token) {
        return given(RequestSpecs.authorizedSpec(token))
                .when()
                .post("/carts");
    }

    public Response getCartWithoutAuthentication(int cartId) {
        return given(RequestSpecs.requestSpec())
                .pathParam("id", cartId)
                .when()
                .get("/carts/{id}");
    }

    public Response addItem(String token, int cartId, String sku, int qty) {
        return given(RequestSpecs.authorizedSpec(token))
                .pathParam("id", cartId)
                .body(Map.of("sku", sku, "qty", qty))
                .when()
                .post("/carts/{id}/items");
    }
}