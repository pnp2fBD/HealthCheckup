Feature: Contact Centre agent login to the application and search for an existing policy

  Scenario: Contact centre login and search policy
    Given User is on contact centre login screen
    Then enters login credentials
      | Fields   | Value    |
      | Username | VIJO     |
      | Password | Passw0rd |
    Then clicks on login button
    And clicks on Take Call
    Then Enter policy number
    Then clicks on Search button
    And check the policy status
    And check the authorised contact
    Then click on Verify
    Then verify document generation
    Then click on Complete
    And Logout from the application
    And close the browser