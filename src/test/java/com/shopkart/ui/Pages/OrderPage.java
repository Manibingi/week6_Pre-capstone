package com.shopkart.ui.Pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.Locators.XpathLocators;

import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {

    public OrderPage verifyStatus(String expectedStatus) {
        $x(XpathLocators.ORDER_STATUS).shouldHave(Condition.exactText(expectedStatus));
        return this;
    }

    public long getOrderId() {
        String text = $x(XpathLocators.ORDER_NUMBER)
                .shouldBe(Condition.visible)
                .getText();
        String orderNumber = text.replaceAll("\\D+", "");
        return Long.parseLong(orderNumber);
    }
}