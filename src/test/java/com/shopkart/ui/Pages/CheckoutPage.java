package com.shopkart.ui.Pages;

import com.shopkart.ui.Locators.XpathLocators;
import static com.codeborne.selenide.Selenide.$x;
import com.shopkart.ui.Pages.OrderPage;
public class CheckoutPage {

    public CheckoutPage enterAddress(String address) {
        $x(XpathLocators.ADDRESS).setValue(address);
        return this;
    }

    public OrderPage placeOrder() {
        $x(XpathLocators.PLACE_ORDER).click();
        return new OrderPage();
    }
}