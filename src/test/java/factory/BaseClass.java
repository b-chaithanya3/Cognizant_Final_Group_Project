package factory;

//import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    static Properties config_p;

    static Logger capture;

    //  private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver initializeBrowser() throws IOException {
        config_p = getProperties();
        String executionEnv = config_p.getProperty("execution_env");
        String browser = config_p.getProperty("browser");
        String os = config_p.getProperty("os");

        if (executionEnv.equalsIgnoreCase("remote")) {
            DesiredCapabilities test_dc = new DesiredCapabilities();
            //OS
            if (os.equalsIgnoreCase("windows")) {
                test_dc.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                test_dc.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("linux")) {
                test_dc.setPlatform(Platform.LINUX);
            } else {
                System.out.println("No matching OS");
                return null;
            }
            //Browser
            switch (browser.toLowerCase()) {
                case "chrome":
                    test_dc.setBrowserName("chrome");
                    break;
                case "edge":
                    test_dc.setBrowserName("MicrosoftEdge");
                    break;
                case "firefox":
                    test_dc.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("No matching browser..");
                    return null;
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), test_dc);
        }
        if (executionEnv.equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    //options.addArguments("--disable-notifications");
                    driver = new ChromeDriver();
                    options.addArguments("--disable-notifications");
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--disable-notifications");
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions=new FirefoxOptions();
                    firefoxOptions.addArguments("--disable-notifications");
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("No matching browser..");
                    driver = null;
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;

    }

    public static WebDriver getdriver() {
        return driver;
    }

    public static Properties getProperties() throws IOException {
        FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
        config_p = new Properties();
        config_p.load(file);
        return config_p;
    }

    public static Logger getLogger() {
        capture = LogManager.getLogger();
        return capture;
    }
}