Feature: Home Loan Calculator

  Scenario: Calculate EMI for Home Loan
    Given I am on the homepage
    When I navigate to the Home Loan Calculator
    And I enter home loan amount "200000", interest rate "6", and tenure "20"
    Then I should see the Home Loan EMI value displayed
    And I capture the Home Loan EMI table data into Excel file "HomeLoanEMI.xlsx"
