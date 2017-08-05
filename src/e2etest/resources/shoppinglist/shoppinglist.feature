Feature: shopping list

  Background:
    Given app has started
    And app is health

  Scenario: Register a new shopping list
    Given I have an empty list
    And the list has "2" "cereals" and check status is "true"
    And the list has "1" "pasta" and check status is "false"
    When I request the list creation
    Then I should receive a valid list with an id

  Scenario: Try to register a new shopping list without be authenticated
    Given I have an empty list
    And the list has "2" "cereals" and check status is "true"
    And the list has "1" "pasta" and check status is "false"
    When I request the list creation without be authenticated
    Then The list creation should not be allowed

  Scenario: Try to register an new but invalid shopping list
    Given I have an empty list
    And the list has "2" "cereals" and check status is "true"
    And the list has "1" "pasta" and check status is "false"
    And I add version number into my shopping list
    When I request the list creation
    Then I should receive a bad request error