package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private By verifybtn= By.id("add-to-cart-sauce-labs-backpack");
    private By producttitle=By.className("inventory_item_name");
    private By productprice=By.className("inventory_item_price");
    private By cartlink=By.className("shopping_cart_link");
    private By hamburger=By.id("react-burger-menu-btn");
    private By logoutbtn=By.id("logout_sidebar_link");
    private WebDriver driver;

    public ProductPage(WebDriver driver){
        this.driver=driver;
    }

    public boolean isproducpagedisplayed(){
        return driver.findElement(verifybtn).isDisplayed();
    }

    public boolean isproductslistdisplayed(){
        return !driver.findElements(producttitle).isEmpty();
    }

    public String geturl(){
        return driver.getCurrentUrl();
    }

    public void addfirstitemtocart(){
        driver.findElement(verifybtn).click();
    }

    public void additemtocart(String productname){
        driver.findElement(By.xpath("//div[text()=\""+productname+"\"]/parent::a/parent::div/following-sibling::div/button")).click();
    }

    public boolean isproducttitledisplayed(String productname){
        return driver.findElement(By.xpath("//div[text()=\""+productname+"\"]")).isDisplayed();
    }

    public boolean isproductpricedisplayed(String productname){
        return driver.findElement(By.xpath("//div[text()=\""+productname+"\"]/parent::a/parent::div/following-sibling::div/div")).isDisplayed();
    }
    public String getproductprice(String productname){
        return driver.findElement(By.xpath("//div[text()=\""+productname+"\"]/parent::a/parent::div/following-sibling::div/div")).getText();
    }

    public boolean isproductincart(String productname){
        driver.findElement(cartlink).click();
        return driver.getPageSource().contains(productname);
    }

    public Cartpage gotocart(){
        driver.findElement(cartlink).click();
        return new Cartpage(driver);
    }
    public void logout(){
        driver.findElement(hamburger).click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutbtn));
        driver.findElement(logoutbtn).click();
    }

}
