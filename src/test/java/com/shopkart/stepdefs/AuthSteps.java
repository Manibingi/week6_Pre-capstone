package com.shopkart.stepdefs;

import com.shopkart.api.specs.ResponseSpecs;
import com.shopkart.data.db.OrderRepository;
import com.shopkart.data.secret.Secrets;
import com.shopkart.support.WorldContext;
import com.shopkart.ui.Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

public class AuthSteps {

    private final WorldContext world;
    private final OrderRepository repository = new OrderRepository();

    public AuthSteps(WorldContext world) {
        this.world = world;
    }

    @Given("{string} is logged in")
    public void userIsLoggedIn(String user) {

        String email = Secrets.email(user);
        String password = Secrets.password(user);
        Response response = world.getAuthClient().login(email, password);
        response.then().spec(ResponseSpecs.ok200());

        String token = response.jsonPath().getString("token");
        Number customerId = response.jsonPath().get("customerId");

        world.setCurrentUser(user);
        world.setToken(token);

        long resolvedCustomerId = customerId == null ? repository.customerId(user) : customerId.longValue();

        world.setCustomerId(resolvedCustomerId);
        world.setInitialPlacedOrders(repository.placedOrders(resolvedCustomerId));

        LoginPage loginPage = new LoginPage();

        world.setHomePage(loginPage.login(email, password));
    }
}