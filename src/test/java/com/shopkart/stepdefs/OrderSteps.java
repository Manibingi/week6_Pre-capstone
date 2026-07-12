package com.shopkart.stepdefs;

import com.shopkart.api.specs.ResponseSpecs;
import com.shopkart.data.OrderBuilder;
import com.shopkart.data.db.OrderRepository;
import com.shopkart.data.secret.Secrets;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderSteps {

    private final WorldContext world;
    private final OrderRepository repository = new OrderRepository();

    public OrderSteps(WorldContext world) {
        this.world = world;
    }

    @Then("the order confirmation shows status {string}")
    public void verifyOrderStatus(String status) {
        world.getOrderPage().verifyStatus(status);
    }

        @Then("the order confirmation should show {string}")
        public void verifyOrderStatusFromConfirmation(String status) {
                verifyOrderStatus(status);
        }

        @Then("the order total should be {int} paise")
        public void verifyOrderTotal(int total) {
                assertEquals(total, repository.totalPaise(world.getOrderId()));
        }

        @Then("the order should be retrievable through the API")
        public void verifyOrderRetrievableFromApi() {
                world.getOrderClient().getOrder(world.getToken(), (int) world.getOrderId())
                                .then()
                                .spec(ResponseSpecs.ok200())
                                .body("orderId", equalTo((int) world.getOrderId()));
        }

    @Then("the order API returns status {string} and totalPaise {int}")
    public void verifyOrderApi(String status, int total) {
        world.getOrderClient().getOrder(world.getToken(),
                        (int) world.getOrderId())
                .then()
                .spec(ResponseSpecs.ok200())
                .body("status", equalTo(status))
                .body("totalPaise", equalTo(total));
    }

    @Given("{string} has a PLACED order")
    public void placedOrder(String user) {
        var login = world.getAuthClient()
                        .login(Secrets.email(user),Secrets.password(user))
                        .then()
                        .spec(ResponseSpecs.ok200())
                        .extract();

        String token = login.path("token");
        Number customerId = login.path("customerId");

        world.setToken(token);
        world.setCustomerId(customerId.longValue());

        Number cartId = world.getCartClient()
                        .createCart(token)
                        .then()
                        .spec(ResponseSpecs.created201())
                        .extract()
                        .path("cartId");

        world.getCartClient()
                .addItem(token, cartId.intValue(), "SKU-BAG", 1)
                .then()
                .spec(ResponseSpecs.ok200());

        Map<String, Object> order = OrderBuilder.anOrder()
                        .withCartId(cartId.intValue())
                        .build();

        Number orderId = world.getOrderClient()
                        .placeOrder(token, order)
                        .then()
                        .spec(ResponseSpecs.created201())
                        .extract()
                        .path("orderId");

        world.setCartId(cartId.longValue());
        world.setOrderId(orderId.longValue());
    }

    @When("{string} requests the order")
    public void anotherUserReadsOrder(String user) {

        String token = world.getAuthClient()
                .login(Secrets.email(user), Secrets.password(user))
                .then()
                .spec(ResponseSpecs.ok200())
                .extract()
                .path("token");

        world.setResponse(world.getOrderClient().getOrder(token, (int) world.getOrderId()));
    }

    @When("she cancels the order")
    public void cancelOrder() {
        world.setResponse(world.getOrderClient()
                        .cancelOrder(
                                world.getToken(),
                                (int) world.getOrderId()));
    }

    @When("she cancels the same order again")
    public void cancelAgain() {
        world.setResponse(world.getOrderClient().cancelOrder(
                                world.getToken(),
                                (int) world.getOrderId()));
    }

    @When("she adds {int} x {string} to the cart")
    public void outOfStock(int qty, String sku) {
        world.setResponse(world.getCartClient().addItem(
                                world.getToken(),
                                (int) world.getCartId(), sku, qty));
    }

    @Then("the orders table has exactly one PLACED row for {string}")
    public void verifyPlacedOrders(String user) {
        assertEquals(world.getInitialPlacedOrders() + 1, repository.placedOrders(world.getCustomerId()));
    }

        @Then("exactly one PLACED order should exist for {string} in the database")
        public void verifyPlacedOrdersForUser(String user) {
                verifyPlacedOrders(user);
        }

    @Then("the order status should become {string}")
    public void verifyDatabaseStatus(String status) {
        assertEquals(status, repository.status(world.getOrderId()));
    }

        @Then("the order status should be {string}")
        public void verifyDatabaseStatusAlias(String status) {
                verifyDatabaseStatus(status);
        }
}