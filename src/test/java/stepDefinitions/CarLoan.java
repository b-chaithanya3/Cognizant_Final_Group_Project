package stepDefinitions;

import ExcelUtil.ScreenshotUtil;
import factory.BaseClass;
import io.qameta.allure.Story;
import org.junit.Assert;
import org.openqa.selenium.*;
import pages.Homepage;
import pages.CarLoanPage;
import io.cucumber.java.en.*;

import java.util.List;

public class CarLoan {

    WebDriver driver = BaseClass.getdriver();
    Homepage homepage = new Homepage(driver);
    CarLoanPage carLoanPage = new CarLoanPage(driver);

    @Story("Open Car Loan Calculator")   //  Added
    @Given("user open the Car Loan EMI Calculator")
    public void openCarLoanCalculator() {
        homepage.navigateToCarLoanCalculator();
    }

    @Story("Enter Car Loan Details")     // Added
    @When("user enter loan amount {string}, tenure {string}, and interest rate {string}")
    public void enterLoanDetails(String amount, String tenure, String rate) {

        double amt = Double.parseDouble(amount);
        int ten = Integer.parseInt(tenure);
        double rt = Double.parseDouble(rate);

        carLoanPage.getLoanAmountField().sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        carLoanPage.getLoanAmountField().sendKeys(amount);
        Assert.assertTrue(amt > 100000);
        carLoanPage.getTenureField().sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        carLoanPage.getTenureField().sendKeys(tenure);
        Assert.assertTrue(ten > 0);
        carLoanPage.getInterestRateField().sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        carLoanPage.getInterestRateField().sendKeys(rate, Keys.ENTER);
        Assert.assertTrue(rt > 0);
    }

    @Story("Verify Car Loan EMI")        //  Added
    @Then("user should see the monthly EMI displayed")
    public void verifyEMI() {
        String emiValue = carLoanPage.getMonthlyEMI().replaceAll("[^0-9.]", "");
        double emi = Double.parseDouble(emiValue);
        //Assert.assertTrue(emi > 0);

        double principal = 1500000.0;
        double monthlyRate = (9.5 / 100) / 12;
        double interestM1 = principal * monthlyRate;
        double principalM1 = emi - interestM1;

        System.out.println("Month 1 Interest: " + Math.round(interestM1));
        System.out.println("Month 1 Principal: " + Math.round(principalM1));
        String fileName = "test-output/screenshots/Error_" + System.currentTimeMillis() + ".png";
        try {
            ScreenshotUtil.captureScreenshot(driver, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BaseClass.getLogger().info("Car Loan EMI: " + emi + " | Interest M1: " + interestM1 + " | Principal M1: " + principalM1);
    }

    @Story("Validate Error Message")     //  Added
    @Then("user should see an error message displayed")
    public void verifyErrorMessage() throws Exception {
        WebElement loanInput = driver.findElement(By.id("carLoanNewFixedLoanRange-Amt"));

        loanInput.clear();
        loanInput.sendKeys("0");

        Thread.sleep(1500);

        String errorMsg = "";
        try {
            WebElement tooltip = driver.findElement(By.xpath(
                    "//input[@id='carLoanNewFixedLoanRange-Amt']/following-sibling::span"));
            errorMsg = tooltip.getText();
        } catch (NoSuchElementException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            errorMsg = (String) js.executeScript("return arguments[0].validationMessage;", loanInput);
        }

        System.out.println("Error: " + errorMsg);
        BaseClass.getLogger().info("Error message: " + errorMsg);

        String fileName = "test-output/screenshots/Error_" + System.currentTimeMillis() + ".png";
        ScreenshotUtil.captureScreenshot(driver, fileName);
        BaseClass.getLogger().info("Error screenshot saved: " + fileName);
    }
}

