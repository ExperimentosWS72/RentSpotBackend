Feature: Send message with the author´s post
  Scenario: Send message correctly
    Given the user navigates to the "postDetails" section
    And press the "Send message" button
    When the user enter the message
    And press the "Send" button
    Then the application send the message from author´s post.