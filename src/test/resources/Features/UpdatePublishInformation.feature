Feature: Update a property listing
  Scenario: Edit a property listing information
    Given the lessor is on their profile page
    When the lessor click on the "Property List" button
    And press "Edit" button
    And fill the Form with the data correctly
    Then a post will be update successfully