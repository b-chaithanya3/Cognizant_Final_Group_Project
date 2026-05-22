package stepDefinitions;

import factory.BaseClass;
import io.qameta.allure.Story;
import org.junit.Assert;
import pages.Homepage;
import pages.HomeLoanPage;
import io.cucumber.java.en.*;
import ExcelUtil.ExcelUtil;

import org.openqa.selenium.WebDriver;

import java.util.List;

public class Homeloan{

    WebDriver driver = BaseClass.getdriver();
    Homepage homepage = new Homepage(driver);
    HomeLoanPage homeLoanPage = new HomeLoanPage(driver);
    @Story("Open Home Loan Calculator")   //  Added
    @Given("user open the Home Loan EMI Calculator")
    public void openHomeLoanCalculator() {
        homepage.navigateToHomeLoanCalculator();
    }

    @Story("Enter Home Loan Details")     //  Added
    @When("user enter home loan amount {string}, tenure {string}, and interest rate {string}")
    public void enterHomeLoanDetails(String amount, String tenure, String rate) {
        homeLoanPage.enterLoanDetails(amount, tenure, rate);
    }

    @Story("Verify Home Loan EMI")        //  Added
    @Then("user should see the monthly EMI for home loan displayed")
    public void verifyHomeLoanEMI() {
        String emivalue= homeLoanPage.getMonthlyEMI();
        String cleanedEMI = emivalue.replaceAll("[^0-9.]", "");
        double emi = Double.parseDouble(cleanedEMI);

        Assert.assertTrue(emi > 0);
        System.out.println("Home Loan EMI: " + emi);
        BaseClass.getLogger().info("Home Loan EMI: " + emi);
    }

    @Story("Store EMI Data in Excel")     // ✅ Added
    @Then("user should store EMI details in Excel")
    public void storeEMIInExcel() throws Exception {
        List<String[]> tableData = homeLoanPage.extractYearlyTableData();
        ExcelUtil.writeFullTable("HomeLoanData", tableData);
        BaseClass.getLogger().info("Home Loan amortization table stored in Excel.");
    }
}
