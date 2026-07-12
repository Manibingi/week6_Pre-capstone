@api
@negative

Feature: Order cancellation

  Scenario: A placed order can only be cancelled once

    Given "alice" has a PLACED order
    When she cancels the order
    Then the order status should be "CANCELLED"

    When she cancels the same order again
    Then the response status should be 409