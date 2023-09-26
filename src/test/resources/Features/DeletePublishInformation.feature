Feature: Publish a property listing
  Scenario: Delete a property listing
    Given the lessor is on their profile page
    When the lessor click on the "Property List" button
    And press "Delete" button
    Then a post will be delete successfully