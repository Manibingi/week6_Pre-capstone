@api
@db

Feature: Cart totals

  Scenario: Total equals quantity multiplied by price

    Given "alice" has an empty cart
    When she adds 2 x "SKU-BAG" to the cart
    Then the cart total should be 99800 paise
    And the database stores totalPaise as 99800