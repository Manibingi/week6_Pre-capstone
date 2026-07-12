@api
@negative

Feature: Out of stock validation

  Scenario: Customer cannot add an out-of-stock product

    Given "alice" has an empty cart
    When she adds 1 x "SKU-CAP" to the cart
    Then the response status should be 409