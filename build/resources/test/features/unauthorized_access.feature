@api
@negative
@security

Feature: Unauthorized access

  Scenario: Accessing a protected endpoint without authentication

    When an unauthenticated user requests the cart
    Then the response status should be 401