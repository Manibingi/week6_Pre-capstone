package com.shopkart.stepdefs;

import com.shopkart.api.specs.ResponseSpecs;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ProductSteps {

    private final WorldContext world;

    public ProductSteps(WorldContext world) {
        this.world = world;
    }

    @When("she searches for {string}")
    public void searchProduct(String product) {
        world.getHomePage().searchProduct(product);
    }

    @Then("the product {string} is displayed")
    public void verifyProduct(String product) {
        world.getHomePage().verifyProductVisible(product);
    }

    @Then("the product API returns product {string}")
    public void verifyProductApi(String sku) {
        world.getProductClient()
                .searchProducts("Bag")
                .then()
                .spec(ResponseSpecs.ok200())
                .body("size()", greaterThan(0))
                .body("[0].sku", equalTo(sku));
    }
}