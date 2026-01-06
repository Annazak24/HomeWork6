#launguage: en

Feature: Course page

 Background:
    Given I open browser Chrome
    And Courses catalog page is opened

 Scenario: Search and open a specific course by name
  When Click on a random course
  Then Course page is opened successfully


 Scenario: Find courses starting on or after a specific date
    When I search for courses starting on or after 01.09.2025
    Then I print courses information to console


  Scenario: Find the cheapest and the most expensive preparatory courses
    When I open "Preparatory courses" section
    And I find the cheapest and the most expensive course
    Then I print selected courses information to console