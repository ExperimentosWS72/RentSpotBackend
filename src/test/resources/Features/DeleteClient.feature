Feature: Delete clients
  Scenario: Delete a tenant in the list
    Given the lessor is on their profile page
    When the lessor click on the "Tenant List" button
    And the lessor click in "delete" button
    Then they can delete a specific tenant.