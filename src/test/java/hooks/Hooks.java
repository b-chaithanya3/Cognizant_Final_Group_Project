package hooks;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Properties;

public class Hooks {

    WebDriver driver;
    Properties config_p;

  @Before
  public void setup() throws IOException
  {
      driver= BaseClass.initializeBrowser();
      config_p=BaseClass.getProperties();
      driver.get(config_p.getProperty("appURL"));
      driver.manage().window().maximize();
  }

  @After
  public void tearDown(Scenario scenario)
  {
      driver.quit();
  }

@AfterStep
  public void addScreenshot(Scenario scenario)
  {
      //this is for cucumber junit report
      if(scenario.isFailed())
      {
          TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
          byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
          scenario.attach(screenshot,"image/png",scenario.getName());
      }
  }
}
