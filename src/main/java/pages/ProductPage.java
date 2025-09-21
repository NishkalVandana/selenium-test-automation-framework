package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private static final Logger log = LogManager.getLogger(ProductPage.class);
    private By verifybtn= By.id("add-to-cart-sauce-labs-backpack");
    private By producttitle=By.className("inventory_item_name");
    private By productprice=By.className("inventory_item_price");
    private By cartlink=By.className("shopping_cart_link");
    private By hamburger=By.id("react-burger-menu-btn");
    private By logoutbtn=By.id("logout_sidebar_link");
    private WebDriver driver;

    public ProductPage(WebDriver driver){
        log.debug("Driver is being intialized");
        this.driver=driver;
    }

    public boolean isproducpagedisplayed(){
        log.debug("Checking if verify button is displayed");
        return driver.findElement(verifybtn).isDisplayed();
    }

    public boolean isproductslistdisplayed(){
        log.debug("check if the elements are displayed");
        return !driver.findElements(producttitle).isEmpty();
    }

    public String geturl(){
        log.debug("Getting the url");
        return driver.getCurrentUrl();
    }

    public void addfirstitemtocart(){
        driver.findElement(verifybtn).click();
    }

    public void additemtocart(String productname){
        log.info("Adding "+productname+" to cart");
        driver.findElement(By.xpath("//div[text()=\""+productname+"\"]/parent::a/parent::div/following-sibling::div/button")).click();
    }

    public boolean isproducttitledisplayed(String productname){
        return driver.findElement(By.xpath("//div[text()=\""+productname+"\"]")).isDisplayed();
    }

    public boolean isproductpricedisplayed(String productname){
        log.debug("Checking if the product prices are being displayed");
        return driver.findElement(By.xpath("//div[text()=\""+productname+"\"]/parent::a/parent::div/following-sibling::div/div")).isDisplayed();
    }
    public String getproductprice(String productname){
        log.debug("Getting the product price");
        return driver.findElement(By.xpath("//div[text()=\""+productname+"\"]/parent::a/parent::div/following-sibling::div/div")).getText();
    }

    public boolean isproductincart(String productname){
        log.info("Checking if the product is added into the cart");
        driver.findElement(cartlink).click();
        return driver.getPageSource().contains(productname);
    }

    public Cartpage gotocart(){
        log.info("Trying to move into cart page");
        driver.findElement(cartlink).click();
        return new Cartpage(driver);
    }
    public void logout(){
        log.info("Trying to logout");
        driver.findElement(hamburger).click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutbtn));
        driver.findElement(logoutbtn).click();
    }

}
