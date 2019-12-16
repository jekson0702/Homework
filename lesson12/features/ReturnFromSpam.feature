@returnFromSpamFeature
Feature: Test Spam page

  Scenario: Return message from spam
    Given I am on Spam Page
    When I choose first message in Spam
    When I click 'Не спам'
    Then I see 'Письмо перемещено в папку Входящие'.