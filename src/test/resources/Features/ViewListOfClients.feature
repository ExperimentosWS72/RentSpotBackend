Feature: List of clients
  Scenario: View the list of tenants
    Given the lessor is on their profile page
    When the lessor click on the "Tenant List" button
    Then they can view their list of tenants.
