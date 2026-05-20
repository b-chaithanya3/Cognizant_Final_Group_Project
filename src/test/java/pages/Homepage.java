package pages;

import factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    By homeloan=By.xpath("//a[normalize-space()='Home Loan']");
    By carloan=By.xpath("//a[normalize-space()='Car Loan']");
    public void goToHomeLoan() {
        //driver.findElement(homeloan).click();

        wait.until(ExpectedConditions.elementToBeClickable(homeloan)).click();
    }
    public void goToLoanCalculator() {
        //driver.findElement(carloan).click();
        wait.until(ExpectedConditions.elementToBeClickable(carloan)).click();
    }
}
