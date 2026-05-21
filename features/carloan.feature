Feature: Car Loan Calculator

  Scenario: Calculate EMI for Car Loan
    Given I am on the mainpage
    When I navigate to the Car Loan Calculator
    And I enter car loan amount "1500000", interest rate "9.5", and tenure "1"
    Then I should see the Car Loan EMI value displayed
    And I capture the Car Loan EMI table data into Excel file "CarLoanEMI.xlsx"
