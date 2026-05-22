package pages;

import factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Homepage extends BasePage {
    WebDriverWait wait;
    public Homepage(WebDriver driver)
    {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @FindBy(xpath = "//a[contains(@data-labeltext,'Discover Products')]")
    WebElement discoverProducts;

    //  Arrow icon inside Discover Products
    @FindBy(xpath = "//a[contains(@data-labeltext,'Discover Products')]//span[contains(@class,'icon-arrow-down')]")
    WebElement discoverArrow;

    //  Dynamic locators (appear after clicking arrow)
    By calculatorMenu = By.xpath("//a[contains(text(),'Calculator')]");
    By carLoan = By.xpath("//a[contains(text(),'Car Loan EMI')]");
    By homeLoan = By.xpath("//a[contains(text(),'Home Loan EMI')]");


    //  Click Discover Arrow FIRST
    private void openDiscoverProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(discoverArrow));
        discoverArrow.click();
    }

    public void navigateToCarLoanCalculator() {

        //  Step 1: Open dropdown
        openDiscoverProducts();

        //  Step 2: Click Calculator
        WebElement calculator = wait.until(
                ExpectedConditions.visibilityOfElementLocated(calculatorMenu));
        calculator.click();

        //  Step 3: Click Car Loan EMI
        WebElement carLoanOption = wait.until(
                ExpectedConditions.elementToBeClickable(carLoan));
        carLoanOption.click();
    }

    public void navigateToHomeLoanCalculator() {

        //  Step 1: Open dropdown
        openDiscoverProducts();

        //  Step 2: Click Calculator
        WebElement calculator = wait.until(
                ExpectedConditions.visibilityOfElementLocated(calculatorMenu));
        calculator.click();

        //  Step 3: Click Home Loan EMI
        WebElement homeLoanOption = wait.until(
                ExpectedConditions.elementToBeClickable(homeLoan));
        homeLoanOption.click();
    }
}
