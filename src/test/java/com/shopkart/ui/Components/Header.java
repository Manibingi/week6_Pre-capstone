package com.shopkart.ui.Components;

import com.shopkart.ui.Locators.XpathLocators;
import com.shopkart.ui.Pages.CartPage;
import com.shopkart.ui.Pages.HomePage;
import com.shopkart.ui.Pages.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Header {

    public CartPage clickCart() {
        $x(XpathLocators.CART).click();
        return new CartPage();
    }
}