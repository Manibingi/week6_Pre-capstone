package com.shopkart.stepdefs;

import com.shopkart.api.specs.ResponseSpecs;
import com.shopkart.data.db.CartRepository;
import com.shopkart.data.db.OrderRepository;
import com.shopkart.data.secret.Secrets;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartSteps {

    private final WorldContext world;
    private final CartRepository repository = new CartRepository();
        private final OrderRepository orderRepository = new OrderRepository();

    public CartSteps(WorldContext world) {
        this.world = world;
    }

    @Given("{string} has an empty cart")
    public void hasEmptyCart(String user) {
        String email = Secrets.email(user);
        String password = Secrets.password(user);

        ExtractableResponse<Response> login = world.getAuthClient()
                        .login(email, password)
                        .then()
                        .spec(ResponseSpecs.ok200())
                        .extract();

        world.setCurrentUser(user);
        world.setToken(login.path("token"));
        world.setCustomerId(orderRepository.customerId(user));

        int cartId = world.getCartClient()
                .createCart(world.getToken())
                .then()
                .spec(ResponseSpecs.created201())
                .extract()
                .path("cartId");
        world.setCartId(cartId);
    }

    @Given("she adds {int} x {string} to her cart")
    public void addItems(int qty, String sku) {
                var product = world.getProductClient()
                                .getProduct(sku)
                                .then()
                                .spec(ResponseSpecs.ok200())
                                .extract();

                String productName = product.path("name");
                if (productName == null || productName.isBlank()) {
                        productName = product.path("title");
        }

                if (productName == null || productName.isBlank()) {
                        throw new IllegalStateException("No product name found for sku: " + sku);
                }

                for (int i = 0; i < qty; i++) {
                        world.getHomePage().product(productName).addToCart();
                }
    }

    @When("she checks out with a valid address")
    public void checkout() {
        world.setCartPage(world.getHomePage().header().clickCart());
        world.setCheckoutPage(world.getCartPage().checkout());
        world.setOrderPage(world.getCheckoutPage().enterAddress("123 Anna Salai, Chennai 600001").placeOrder());
        world.setOrderId(world.getOrderPage().getOrderId());
    }

    @Then("the cart total should be {int} paise")
    public void verifyCartTotal(int total) {
        assertEquals(total, repository.totalPaise(world.getCartId()));
    }

        @Then("the database stores totalPaise as {int}")
        public void verifyDatabaseTotalPaise(int total) {
                verifyCartTotal(total);
        }
}