@uncheckMessages
Feature: Uncheck messages with flags

  Scenario: UncheckMessagesWithFlags
    Given I am on InboxPage
    When i demark all Flags
    Then I see no flags