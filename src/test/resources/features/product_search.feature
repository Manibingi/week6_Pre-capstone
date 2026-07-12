@smoke
@ui
@api

Feature: Product search

  Scenario: Search returns matching products

    Given "alice" is logged in
    When she searches for "Bag"
    Then the product "Metro Carryall" is displayed
    And the product API returns product "SKU-BAG"