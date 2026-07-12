package com.shopkart.support;

import com.shopkart.api.*;
import com.shopkart.api.specs.RequestSpecs;
import com.shopkart.api.specs.ResponseSpecs;
import com.shopkart.config.AppConfig;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class BaseApiTest {

    protected RequestSpecification request;
    protected AuthClient authClient;
    protected ProductClient productClient;
    protected CartClient cartClient;
    protected OrderClient orderClient;

    @BeforeEach
    void setup() {

        request = RequestSpecs.requestSpec();

        authClient = new AuthClient(request);
        productClient = new ProductClient(request);
        cartClient = new CartClient(request);
        orderClient = new OrderClient(request);
    }

    protected String loginAs(String user) {

        return authClient.login(AppConfig.get(user + ".email"),AppConfig.get(user + ".password"))
                .then()
                .spec(ResponseSpecs.ok200())
                .extract()
                .path("token");
    }
}