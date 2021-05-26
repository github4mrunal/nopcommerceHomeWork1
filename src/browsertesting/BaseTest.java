package browsertesting;

import org.openqa.selenium.chrome.ChromeDriver;
import utility.Utility;

import java.util.concurrent.TimeUnit;

public class BaseTest extends Utility {

    public void openBrowser(String baseUrl){
      //  baseUrl = " https://demo.nopcommerce.com/";
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        //webdriver declaration
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        //method to open browser
        driver.get(baseUrl);

    }
    public void closeBrowser(){
        if (driver != null){
            driver.quit();
        }
    }

}
