package learn.tests;

import org.example.Browser;
import org.example.Configbuilder;
import org.example.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pages.Cartpage;
import pages.LoginPage;
import pages.ProductPage;

public class BaseTest {
    public static WebDriver driver;
    Configbuilder configbuilder=new Configbuilder();
    LoginPage loginPage;
    protected ProductPage productpage;
    protected Cartpage cartpage;

    @BeforeTest
    @Parameters("browsername")
    public void setup(String browsername) throws InterruptedException{
        Browser browser= Browser.valueOf(browsername);
        driver= WebDriverFactory.createDriver(browser);
        driver.get(configbuilder.getproperty("app.uri"));
        loginPage=new LoginPage(driver);
        loginPage.Login("standard_user","secret_sauce");
        productpage=loginPage.clicklogin();
        Thread.sleep(3000);

    }


}
