package com.shopkart.ui.Locators;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public final class XpathLocators {

    private XpathLocators() {
    }

    //cart locators

    public static final String CART_LINE = "//tr[contains(@class,'cart-line')][td[normalize-space()='%s']]";
    public static final String LINE_TOTAL = ".//td[@class='line-total']";
    public static final String CART_TOTAL = "//strong[@data-role='cart-total']";
    public static final String CONTINUE_SHOPPING = "//button[normalize-space()='Continue shopping']";
    public static final String CHECKOUT = "//button[normalize-space()='Checkout']";

    //checkout locators
    public static final String ADDRESS = "//textarea[@id='address']";
    public static final String PLACE_ORDER = "//button[@type='submit' and normalize-space()='Place order']";

    //header locators
    public static final String CART = "//nav[@aria-label='Primary navigation']//button[normalize-space()='Cart']";

    //homepage locators

    public static final String SEARCH_BOX = "//input[@id='catalog-search']";
    public static final String SEARCH_BUTTON = "//button[@type='submit' and normalize-space()='Search']";
    public static final String PRODUCT_BY_NAME = "//div[contains(@class,'product-card')][.//h2/button[normalize-space()='%s']]";


    //login locators

    public static final String EMAIL = "//input[@id='email']";
    public static final String PASSWORD = "//input[@id='password']";
    public static final String SIGN_IN = "//button[@type='submit' and normalize-space()='Sign in']";
    public static final String ERROR_MESSAGE = "//div[@role='alert']";

    //order locators

    public static final String ORDER_NUMBER = "//section[contains(@class,'order-confirmation')]//p";
    public static final String ORDER_STATUS = "//dd[@data-field='order-status']";
    public static final String ORDER_TOTAL = "//dd[@data-field='order-total']";
    public static final String DELIVERY_ADDRESS = "//dt[normalize-space()='Delivery address']/following-sibling::dd";
    public static final String RETURN_TO_CATALOG = "//button[normalize-space()='Return to catalog']";

}

