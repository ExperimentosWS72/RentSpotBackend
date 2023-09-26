Feature: User account creation
  Scenario: Successful user account creation
    Given that the user enters the platform
    When press “Register”
    And complete the form with your data
    And hit the “Register” button
    Then your account was successfully created
  Scenario: Incorrect artist account creation
    Given that the user enters the platform
    When press “Register”
    And incorrectly complete the form
    And hit the “Register” button
    Then will display a message that your account was not created
  Scenario: Register with the user account created
    Given that the user enters the platform
    And already has an account created on the platform
    When complete your corresponding data in the form
    And is a exist user account
    And hit the “Register” button
    Then will display a message that your account was not created