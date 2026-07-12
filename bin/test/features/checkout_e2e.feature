@e2e @smoke
Feature: Checkout places an order

  Scenario: Customer successfully places an order

    Given "alice" is logged in
    And she adds 1 x "SKU-LMP" to her cart
    When she checks out with a valid address
    Then the order confirmation should show "PLACED"
    And the order total should be 79900 paise
    And the order should be retrievable through the API
    And exactly one PLACED order should exist for "alice" in the database