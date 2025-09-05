package pages;

import Exceptions.LoginpageException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    //elements
    private By usernamefield=By.id("user-name");
    private By passwordfield=By.id("password");
    private By lgnbtn=By.id("login-button");
    private By logotext=By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div");
    private By errormsg=By.className("error-message-container");
    protected ProductPage productpage;

    //actions

    public void Login(String name,String password){
        this.sendusername(name);
        this.sendpassword(password);
    }
    public String getlogo(){
        return driver.findElement(logotext).getText();
    }
    private void sendusername(String name){
        driver.findElement(usernamefield).clear();
        driver.findElement(usernamefield).sendKeys(name);
    }
    private void sendpassword(String password){
        driver.findElement(passwordfield).clear();
        driver.findElement(passwordfield).sendKeys(password);
    }
    public ProductPage clicklogin(){
        driver.findElement(lgnbtn).click();
        if(isloginfailed()){
            throw new LoginpageException("Invalid credentials");
        }
        return new ProductPage(driver);
    }
    public boolean isloginfailed(){
        try{
            WebElement err= driver.findElement(errormsg);
            return err.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

}
