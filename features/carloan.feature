Feature: Car Loan Calculator

  Scenario: Calculate EMI for Car Loan
    Given I am on the mainpage
    When I navigate to the Car Loan Calculator
    And I enter car loan amount "50000", interest rate "7", and tenure "5"
    Then I should see the Car Loan EMI value displayed
    And I capture the Car Loan EMI table data into Excel file "CarLoanEMI.xlsx"
