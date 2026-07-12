package com.shopkart.stepdefs;

import com.shopkart.support.WorldContext;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonSteps {

    private final WorldContext world;
    public CommonSteps(WorldContext world) {
        this.world = world;
    }

    @Then("the response status should be {int}")
    public void verifyStatus(int status) {
        assertEquals(status, world.getResponse().statusCode());
    }

    @When("an unauthenticated user requests the cart")
    public void unauthenticatedUserRequestsCart() {
        world.setResponse(world.getCartClient().getCartWithoutAuthentication(1));
    }
}