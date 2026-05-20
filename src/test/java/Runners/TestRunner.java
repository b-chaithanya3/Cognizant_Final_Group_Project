package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
       //features= {".//features/homeloan.feature"},
       features= {".//features"},
       // features= {"@target/rerun.txt"},
        glue={"stepDefinitions","hooks"},
        monochrome = true,//to avoid junk characters in output
        plugin = {
                "pretty",
                "html:reports/cucumber-report.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:target/rerun.txt"
                 },
        dryRun=false
      )

public class TestRunner {

}
