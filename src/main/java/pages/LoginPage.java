package pages;

import Exceptions.LoginpageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        log.debug("LoginPage initialized with driver instance: {}", driver);
    }

    //elements
    private By usernamefield = By.id("user-name");
    private By passwordfield = By.id("password");
    private By lgnbtn = By.id("login-button");
    private By logotext = By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div");
    private By errormsg = By.className("error-message-container");
    protected ProductPage productpage;

    //actions
    public void Login(String name, String password) {
        log.info("Attempting to login with username: {}", name);
        this.sendusername(name);
        this.sendpassword(password);
    }

    public String getlogo() {
        log.debug("Fetching logo text from login page");
        return driver.findElement(logotext).getText();
    }

    private void sendusername(String name) {
        log.debug("Clearing and entering username: {}", name);
        driver.findElement(usernamefield).clear();
        driver.findElement(usernamefield).sendKeys(name);
    }

    private void sendpassword(String password) {
        log.debug("Clearing and entering password");
        driver.findElement(passwordfield).clear();
        driver.findElement(passwordfield).sendKeys(password);
    }

    public ProductPage clicklogin() {
        log.info("Clicking on login button");
        driver.findElement(lgnbtn).click();
        if (isloginfailed()) {
            log.error("Login failed! Invalid credentials entered.");
            throw new LoginpageException("Invalid credentials");
        }
        log.info("Login successful. Navigating to ProductPage.");
        return new ProductPage(driver);
    }

    public boolean isloginfailed() {
        try {
            WebElement err = driver.findElement(errormsg);
            boolean failed = err.isDisplayed();
            if (failed) {
                log.warn("Error message displayed after login attempt");
            }
            return failed;
        } catch (NoSuchElementException e) {
            log.debug("No error message found. Login did not fail.");
            return false;
        }
    }

    public boolean isloginpagedisplayed() {
        boolean displayed = driver.findElement(usernamefield).isDisplayed();
        log.debug("Login page displayed: {}", displayed);
        return displayed;
    }
}
