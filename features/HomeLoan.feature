Feature: Home Loan EMI Calculation

  Scenario: Valid Home Loan EMI
    Given user open the Home Loan EMI Calculator
    When user enter home loan amount "<amount>", tenure "<tenure>", and interest rate "<rate>"
    Then user should see the monthly EMI for home loan displayed
    And user should store EMI details in Excel



    Examples:
      | amount  | tenure | rate |
      | 1500000 | 1      | 9.5  |
      | 2000000 | 3      | 8.2  |
