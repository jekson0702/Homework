@sendMessageFeature
Feature: Test Send message page

  Scenario: Send a message
    Given I am on Inbox Page
    When I click button 'Написать сообщение'
    When I input emails
    When I input message text
    When I click 'Отправить сообщение'
    Then I see 'Письмо отправлено'