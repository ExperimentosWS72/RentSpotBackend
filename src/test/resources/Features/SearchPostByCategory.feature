Feature: Search post with category button
  Scenario: User find results
    Given the user navigates to the "Posts" section
    When they search for a specific category button
    Then a list of results is displayed according the category.
  Scenario: User don´t find results
    Given the user navigates to the "Posts" section
    When they search for a specific category button
    And the applications does not find any results matching the category
    Then no results are displayed.