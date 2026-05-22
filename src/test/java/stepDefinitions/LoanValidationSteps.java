package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Assert;
//import pages.CarLoanCalculatorPage;
import org.openqa.selenium.WebDriver;
import pages.CarLoanPage;
import pages.Homepage;

@Epic("Loan Application")          //  Added
@Feature("Loan Validation")        //  Added
public class LoanValidationSteps{
    WebDriver driver = BaseClass.getdriver();
    Homepage homepage = new Homepage(driver);
    CarLoanPage carLoanPage = new CarLoanPage(driver);
    @Story("Open Loan Calculator")   // Added
    @Given("user open the Loan Calculator")
    public void openLoanCalculator() {
        homepage.navigateToCarLoanCalculator();
    }

    @Story("Validate Loan Fields")   //  Added
    @Then("user validate loan amount, tenure and interest fields are displayed")
    public void validateFields() {
        Assert.assertTrue(carLoanPage.getLoanAmountField().isDisplayed());
        Assert.assertTrue(carLoanPage.getTenureField().isDisplayed());
        Assert.assertTrue(carLoanPage.getInterestRateField().isDisplayed());
    }

    @Story("Move Loan Slider")       //  Added
    @Then("user move the loan slider")
    public void moveSlider() {
        carLoanPage.moveLoanSlider(50); // example offset
    }
}
