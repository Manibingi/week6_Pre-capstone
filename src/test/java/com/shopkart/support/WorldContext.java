package com.shopkart.support;

import com.shopkart.api.AuthClient;
import com.shopkart.api.CartClient;
import com.shopkart.api.OrderClient;
import com.shopkart.api.ProductClient;
import com.shopkart.api.specs.RequestSpecs;
import com.shopkart.ui.Pages.CartPage;
import com.shopkart.ui.Pages.CheckoutPage;
import com.shopkart.ui.Pages.HomePage;
import com.shopkart.ui.Pages.LoginPage;
import com.shopkart.ui.Pages.OrderPage;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WorldContext {



    private final RequestSpecification request = RequestSpecs.requestSpec();
    private final AuthClient authClient = new AuthClient(request);
    private final ProductClient productClient = new ProductClient(request);
    private final CartClient cartClient = new CartClient(request);
    private final OrderClient orderClient = new OrderClient(request);


    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OrderPage orderPage;



    private String currentUser;
    private String token;
    private long customerId;
    private long cartId;
    private long orderId;
    private int initialPlacedOrders;
    private Response response;


    public AuthClient getAuthClient() {
        return authClient;
    }
    public ProductClient getProductClient() {
        return productClient;
    }
    public CartClient getCartClient() {
        return cartClient;
    }
    public OrderClient getOrderClient() {
        return orderClient;
    }



    public LoginPage getLoginPage() {
        return loginPage;
    }
    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }
    public HomePage getHomePage() {
        return homePage;
    }
    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }
    public CartPage getCartPage() {
        return cartPage;
    }
    public void setCartPage(CartPage cartPage) {
        this.cartPage = cartPage;
    }
    public CheckoutPage getCheckoutPage() {
        return checkoutPage;
    }
    public void setCheckoutPage(CheckoutPage checkoutPage) {
        this.checkoutPage = checkoutPage;
    }
    public OrderPage getOrderPage() {
        return orderPage;
    }
    public void setOrderPage(OrderPage orderPage) {
        this.orderPage = orderPage;
    }



    public String getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public long getCartId() {
        return cartId;
    }
    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public int getInitialPlacedOrders() {
        return initialPlacedOrders;
    }
    public void setInitialPlacedOrders(int initialPlacedOrders) {
        this.initialPlacedOrders = initialPlacedOrders;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}