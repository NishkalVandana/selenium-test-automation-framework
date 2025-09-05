package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cartpage {

    private WebDriver driver;
    public Cartpage(WebDriver driver){
        this.driver=driver;
    }
    public boolean isproductincart(String productname){
        return driver.getPageSource().contains(productname);
    }
    public void removeproduct(String productname){
        driver.findElement(By.xpath("//div[text()=\""+productname+"\"]/parent::a/parent::div/div[2]/button")).click();
    }
}
