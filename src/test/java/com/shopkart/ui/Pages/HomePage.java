package com.shopkart.ui.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.shopkart.ui.Components.Header;
import com.shopkart.ui.Components.ProductCard;
import com.shopkart.ui.Locators.XpathLocators;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    private final Header header = new Header();

    public Header header() {
        return header;
    }

    public HomePage searchProduct(String product) {
        $x(XpathLocators.SEARCH_BOX).setValue(product);
        $x(XpathLocators.SEARCH_BUTTON).click();
        return this;
    }

    public ProductCard product(String productName) {
        SelenideElement root = $x(String.format(XpathLocators.PRODUCT_BY_NAME, productName));
        root.shouldBe(Condition.visible);
        return new ProductCard(root);
    }

    public HomePage verifyProductVisible(String productName) {
        product(productName);
        return this;
    }
}