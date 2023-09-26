Feature: Qualification a Tenant profile
  Scenario: Save qualification
    Given the user navigates to the "postDetails" section
    And press the "see author´s profile" link
    When the user enter the qualification
    And press the "Save" button
    Then the application displays the average rating.
  Scenario: Edit qualification
    Given the user navigates to the "postDetails" section
    And press the "see author´s profile" link
    When the user press the button "Edit"
    And press "Save" button
    Then the application saves the changes.