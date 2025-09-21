package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Cartpage {

    private static final Logger log = LogManager.getLogger(Cartpage.class);
    private WebDriver driver;
    public Cartpage(WebDriver driver){
        this.driver=driver;
    }
    public boolean isproductincart(String productname){
        return driver.getPageSource().contains(productname);
    }
    public void removeproduct(String productname){
        log.info("Trying to remove product from cart");
        driver.findElement(By.xpath("//div[text()=\""+productname+"\"]/parent::a/parent::div/div[2]/button")).click();
    }
}
