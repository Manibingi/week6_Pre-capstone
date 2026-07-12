package com.shopkart.ui.Components;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProductCard {

    private final SelenideElement element;

    public ProductCard(SelenideElement element) {
        this.element = element;
    }

    public String getSku() {
        return element.$x(".//div[@class='product-meta']/span[2]").getText();
    }
    public String getStock() {
        return element.$x(".//div[@class='product-footer']/span").getText();
    }

    public ProductCard verifyOutOfStock() {
        element.$x(".//span[contains(@class,'stock')]").shouldHave(Condition.exactText("Out of stock"));
        element.$x(".//button[contains(@class,'quick-add')]").shouldBe(Condition.disabled);
        return this;
    }

    public ProductCard addToCart() {
        element.$x(".//button[contains(@class,'quick-add')]").shouldBe(Condition.enabled).click();
        return this;
    }
}