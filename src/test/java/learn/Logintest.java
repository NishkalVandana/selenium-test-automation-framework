package learn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Logintest {

    WebDriver driver=new ChromeDriver();
    @Test
    public void test1(){
        driver.get("https://www.saucedemo.com/");
        Assert.assertEquals(driver.getTitle(),"Swag Labs");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }
}
