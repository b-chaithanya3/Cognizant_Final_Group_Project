package pages;

import factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CarLoanPage extends BasePage {

    WebDriverWait wait;
    public CarLoanPage(WebDriver driver)
    {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    @FindBy(id="carLoanNewFixedLoanRange-Amt")
    private WebElement loanAmount;

    @FindBy(name="loan_tenure")
    private WebElement tenure;

    @FindBy(name="interest_rate")
    private WebElement interestRate;

    @FindBy(xpath="//p[@class='emi_amt']")
    private WebElement emiAmount;

    // Locator for the Slider Handle based on your HTML
    @FindBy(css=".noUi-handle")
    private WebElement loanSliderHandle;

    public WebElement getLoanAmountField() {
        return loanAmount;
    }

    public WebElement getTenureField() {
        return tenure;
    }

    public WebElement getInterestRateField() {
        return interestRate;
    }

    public String getMonthlyEMI() {
        return emiAmount.getText();
    }

    /**
     * UI Validation Requirement: Move the slider (scroll bar)
     * This moves the handle to simulate user interaction with the scale.
     */
    public void moveLoanSlider(int xOffset) {
        Actions move = new Actions(driver);
        // Drag the handle by a specific horizontal offset
        move.dragAndDropBy(loanSliderHandle, xOffset, 0).build().perform();
    }
}