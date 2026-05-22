Feature: Car Loan EMI Calculation

  Scenario: Valid Car Loan EMI
    Given user open the Car Loan EMI Calculator
    When user enter loan amount "1500000", tenure "1", and interest rate "9.5"
    Then user should see the monthly EMI displayed

