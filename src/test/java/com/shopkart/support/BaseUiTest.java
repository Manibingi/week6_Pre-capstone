package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import com.shopkart.config.AppConfig;

public class BaseUiTest {

    @BeforeEach
    void setup() {


        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        Configuration.timeout = 10000;

        Configuration.headless = false;

        open(AppConfig.get("ui.url"));
    }

}