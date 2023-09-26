Feature: Publish a property listing
  Scenario: Create a property listing
    Given the user navigates to the "Post" section
    And fill the Form with the data correctly
    And press the button "+Post"
    Then a post will be created successfully