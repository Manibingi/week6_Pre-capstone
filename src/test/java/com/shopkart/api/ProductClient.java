package com.shopkart.api;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class ProductClient {

    private final RequestSpecification request;

    public ProductClient(RequestSpecification request) {
        this.request = request;
    }

    public Response searchProducts(String query) {
        return given(request)
                .queryParam("q", query)
                .when()
                .get("/products");
    }

    public Response getProduct(String sku) {
        return given(request)
                .pathParam("sku", sku)
                .when()
                .get("/products/{sku}");
    }
}