package ExcelUtil;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, String filePath) throws Exception {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);



        File destFile = new File(filePath);

        destFile.getParentFile().mkdirs(); // create folder if missing



        // overwrite if file already exists

        Files.copy(src.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }

}