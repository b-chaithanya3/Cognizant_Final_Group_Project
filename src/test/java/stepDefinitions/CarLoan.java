package stepDefinitions;

import factory.BaseClass;
import org.junit.Assert;
import pages.Homepage;
import pages.CarLoanPage;
import ExcelUtil.ExcelUtil;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CarLoan{

    WebDriver driver = BaseClass.getdriver();
    Homepage homepage = new Homepage(driver);
    CarLoanPage carLoanPage = new CarLoanPage(driver);

    @Given("I am on the mainpage")
    public void i_am_on_the_mainpage() {
        // Hooks already navigate to appURL
    }

    @When("I navigate to the Car Loan Calculator")
    public void i_navigate_to_car_loan_calculator() {
        BaseClass.getLogger().info("navigate to car loan calculator");
        homepage.goToLoanCalculator();
    }

    @When("I enter car loan amount {string}, interest rate {string}, and tenure {string}")
    public void i_enter_car_loan_details(String amt, String rate, String time) throws InterruptedException {
        BaseClass.getLogger().info("Entering amount,rate,time");
        carLoanPage.enterLoanDetails(amt, rate, time);
    }

    @Then("I should see the Car Loan EMI value displayed")
    public void i_should_see_the_car_emi_value_displayed() {
        BaseClass.getLogger().info("getting emi value");
        String emi = carLoanPage.getEMI();
        Assert.assertNotEquals(emi,"₹0","EMI should not be zero");
        System.out.println("Car Loan EMI: " + emi);
        String emiValue = carLoanPage.getEMI().replaceAll("[^0-9.]", "");
        double e = Double.parseDouble(emiValue);
        carLoanPage.scrollDown();
        double principal = 1500000.0;
        double monthlyRate = (9.5 / 100) / 12;
        double interestM1 = principal * monthlyRate;
        double principalM1 = e - interestM1;
        System.out.println("Month 1 Interest: " + Math.round(interestM1));
        System.out.println("Month 1 Principal: " + Math.round(principalM1));
    }

    @And("I capture the Car Loan EMI table data into Excel file {string}")
    public void i_capture_car_table_data_into_excel(String filename) {
        BaseClass.getLogger().info("Entering car loan emi data into excel file ");
        List<String[]> tableData = carLoanPage.getTableData();
        ExcelUtil.writeData(tableData,"carloan.xlsx");
    }
}
