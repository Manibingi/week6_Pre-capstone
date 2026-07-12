package com.shopkart.api;

import com.shopkart.api.specs.RequestSpecs;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class OrderClient {

    private final RequestSpecification request;

    public OrderClient(RequestSpecification request) {
        this.request = request;
    }

    public Response placeOrder(String token, Map<String, Object> order) {
        return given(RequestSpecs.authorizedSpec(token))
                .body(order)
                .when()
                .post("/orders");
    }

    public Response getOrder(String token, int orderId) {
        return given(RequestSpecs.authorizedSpec(token))
                .pathParam("id", orderId)
                .when()
                .get("/orders/{id}");
    }

    public Response cancelOrder(String token, int orderId) {
        return given(RequestSpecs.authorizedSpec(token))
                .pathParam("id", orderId)
                .when()
                .post("/orders/{id}/cancel");
    }
}