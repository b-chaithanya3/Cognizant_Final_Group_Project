package stepDefinitions;

import factory.BaseClass;
import org.junit.Assert;
import pages.Homepage;
import pages.HomeLoanPage;
import ExcelUtil.ExcelUtil;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Homeloan{

    WebDriver driver = BaseClass.getdriver();
    Homepage homepage = new Homepage(driver);
    HomeLoanPage homeLoanPage = new HomeLoanPage(driver);

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        // Hooks already navigate to appURL
    }

    @When("I navigate to the Home Loan Calculator")
    public void i_navigate_to_home_loan_calculator() {
        BaseClass.getLogger().info("navigate to Home loan calculator");
        homepage.goToHomeLoan();
    }

    @When("I enter home loan amount {string}, interest rate {string}, and tenure {string}")
    public void i_enter_home_loan_details(String amt, String rate, String time) {
        BaseClass.getLogger().info("Entering amount,rate,time");
        homeLoanPage.enterDetails(amt, rate, time);
    }

    @Then("I should see the Home Loan EMI value displayed")
    public void i_should_see_the_home_loan_emi_value_displayed() {
        BaseClass.getLogger().info("getting emi value");
        String emi = homeLoanPage.getEMI();
        Assert.assertNotEquals(emi,"₹0","EMI should not be zero");
        System.out.println("Home Loan EMI: " + emi);
    }

    @And("I capture the Home Loan EMI table data into Excel file {string}")
    public void i_capture_home_table_data_into_excel(String filename) {
        List<String[]> tableData = homeLoanPage.getYearTable();
        ExcelUtil.writeData(tableData,"Homeloan.xlsx");
        //LogUtil.writeLog("Home Loan amortization table stored in Excel.");
    }
}
