Feature: Self Service login and verify policy

  Scenario: Self Service login and verify policy
    Given User is on self service login screen
    Then clicks on Sign In
    Then Enter login credentials
    Then clicks on Sign In button
    And verify the policy number
    And Logout from the application
    And close the browser
