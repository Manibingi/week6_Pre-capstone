package com.shopkart.ui.Pages;

import com.shopkart.config.AppConfig;
import com.shopkart.ui.Locators.XpathLocators;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public LoginPage openLoginPage() {
        open("/login");        return this;
    }

    public LoginPage enterEmail(String email) {
        $x(XpathLocators.EMAIL).setValue(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        $x(XpathLocators.PASSWORD).setValue(password);
        return this;
    }

    public HomePage clickSignIn() {
        $x(XpathLocators.SIGN_IN).click();
        return new HomePage();
    }

    public HomePage login(String email, String password) {
        return openLoginPage()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignIn();
    }
}