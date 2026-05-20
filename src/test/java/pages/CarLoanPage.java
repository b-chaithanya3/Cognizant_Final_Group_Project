package pages;

import factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    By loanAmount = By.xpath("//input[@id='loanamount']");
    By interest = By.xpath("//input[@id='loaninterest']");
    By tenure = By.xpath("//input[@id='loanterm']");
    By yearsOption = By.xpath("//label[normalize-space()='Yr']");
    By emiValue = By.xpath("//div[@id='emiamount']//p[contains(text(),'₹')]");

    // Actions
    public void enterLoanDetails(String amt, String rate, String time) {
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loanAmount)).clear();
        driver.findElement(loanAmount).sendKeys(amt);

        driver.findElement(interest).clear();
        driver.findElement(interest).sendKeys(rate);

        driver.findElement(tenure).clear();
        driver.findElement(tenure).sendKeys(time);

        driver.findElement(yearsOption).click();
    }

    public String getEMI() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emiValue)).getText();
    }

    public List<String[]> getTableData() {
        //return driver.findElements(By.xpath("//div[@id='emipaymentdetails']//tbody//tr"));
        List<WebElement> scheduleRows=driver.findElements(By.xpath("//div[@id='emipaymentdetails']//tbody//tr"));
        List<String[]> dataList = new ArrayList<>();
        dataList.add(new String[]{"Year", "Principal(A)", "Interest(B)", "Total Payment(A+B)", "Balance", "Loan Paid To Date"});

        for (WebElement row : scheduleRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty()) {
                String[] rowData = new String[cells.size()];
                for (int i = 0; i < cells.size(); i++) {
                    rowData[i] = cells.get(i).getText();
                }
                dataList.add(rowData);
            }
        }
        return dataList;
    }
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }
}