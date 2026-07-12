package com.shopkart.ui.Pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.Components.Header;
import com.shopkart.ui.Locators.XpathLocators;

import static com.codeborne.selenide.Selenide.$x;

public class CartPage {

    private final Header header = new Header();

    public Header header() {
        return header;
    }

    public CheckoutPage checkout() {
        $x(XpathLocators.CHECKOUT).click();
        return new CheckoutPage();
    }
}