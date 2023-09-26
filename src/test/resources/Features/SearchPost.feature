Feature: User Property Search
  Scenario: User searches for a property and finds results
    Given that the User navigates to the "Post´s list" section
    When they finish entering the search criteria
    And execute the search
    And a property search is performed based on their preferences
    Then a list of results is displayed according to their preferences.
  Scenario: User searches for a property and does not find results
    Given that the User navigates to the "Post´s list" section
    When they finish entering the search criteria
    And execute the search
    And the application does not find any results matching the search
    Then no results are displayed.