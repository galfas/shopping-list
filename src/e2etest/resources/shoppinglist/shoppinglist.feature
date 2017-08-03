Feature: statistics retrieval

  Background:
    Given app has started
    And app is health

  Scenario: Register a new shopping list
    Given I have an empty list
    And the list has "2" "cereals" and check status is "true"
    And the list has "1" "pasta" and check status is "false"
    When I request the list creation
    Then I should receive a valid list with an id