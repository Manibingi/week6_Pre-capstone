package com.shopkart.allureReport;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Framework")
@Feature("Reporting")
@Story("Allure Configuration")
@Owner("Mani")
@Tag("framework")
@Tag("reporting")
public class ReportingValidationTest {
    private static final Path CATEGORIES_FILE = Path.of("src/test/resources/categories.json");

    @Test
    @DisplayName("Verify categories.json exists")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensures the Allure categories configuration file is present.")
    void shouldContainCategoriesFile() {
        assertTrue(Files.exists(CATEGORIES_FILE), "categories.json should exist");
    }

    @Test
    @DisplayName("Verify required reporting categories")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensures all required Allure reporting categories are configured.")
    void shouldContainRequiredCategories() throws Exception {
        String json = Files.readString(CATEGORIES_FILE);
        assertAll(
                () -> assertTrue(json.contains("Product Defect")),
                () -> assertTrue(json.contains("UI Locator Failure")),
                () -> assertTrue(json.contains("API Failure")),
                () -> assertTrue(json.contains("Database Failure")),
                () -> assertTrue(json.contains("Authentication / Authorization")),
                () -> assertTrue(json.contains("Configuration Issue")),
                () -> assertTrue(json.contains("Browser / Infrastructure")),
                () -> assertTrue(json.contains("Flaky Test")),
                () -> assertTrue(json.contains("Unclassified Failure"))

        );
    }

    @Test
    @DisplayName("Verify category ordering")
    @Severity(SeverityLevel.NORMAL)
    @Description("Ensures specific categories appear before the fallback category.")
    void shouldPlaceSpecificCategoriesBeforeFallback() throws Exception {
        String json = Files.readString(CATEGORIES_FILE);
        int productDefect = json.indexOf("Product Defect");
        int fallback = json.indexOf("Unclassified Failure");
        assertTrue(productDefect < fallback, "Specific categories should appear before fallback.");
    }

    @Test
    @DisplayName("Verify important regex patterns")
    @Severity(SeverityLevel.NORMAL)
    @Description("Ensures important exception patterns are defined in categories.json.")
    void shouldContainExpectedRegexPatterns() throws Exception {
        String json = Files.readString(CATEGORIES_FILE);
        assertAll(
                () -> assertTrue(json.contains("AssertionFailedError")),
                () -> assertTrue(json.contains("NoSuchElementException")),
                () -> assertTrue(json.contains("SQLException")),
                () -> assertTrue(json.contains("401")),
                () -> assertTrue(json.contains("500"))
        );
    }
}