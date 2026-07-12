package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.shopkart.config.AppConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.open;

public class Hooks {

    @Before
    public void beforeScenario() {
        Configuration.baseUrl = AppConfig.get("ui.url");

        Configuration.browser = AppConfig.get("browser");
        Configuration.browserSize = AppConfig.get("browser.size");
        Configuration.timeout = AppConfig.getInt("timeout");
        Configuration.headless = AppConfig.getBoolean("headless");

    }

    @After
    public void afterScenario() {
        Selenide.closeWebDriver();
    }
}