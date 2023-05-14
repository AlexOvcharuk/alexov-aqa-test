@search
Feature: Search for the product

  Scenario Outline: Get <product> product
    When he searches for <product>
    Then he sees the results displayed for <product>
    Examples:
      | product |
      | orange  |
      | apple   |
      | pasta   |
      | cola    |


  Scenario Outline: Get not existing <product> product and see no results
    When he searches for <product>
    Then he does not see the results
    Examples:
      | product   |
      | mango     |
      | car       |
      | raspberry |





