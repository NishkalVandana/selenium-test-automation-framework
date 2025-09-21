package learn.tests;

import Exceptions.LoginpageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginFlow extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginFlow.class);

    @Test(priority = 1)
    public void LoginPassTestS() {
        log.debug("Fetching logo text from login page");
        String logotxt = loginPage.getlogo();
        Assert.assertEquals(logotxt, "Swag Labs", "Logo text mismatch");
        log.info("Verified logo text successfully");

        // product page actions
        log.info("Verifying if product page is displayed");
        assertTrue(productpage.isproducpagedisplayed(), "Product page is not displayed");
        log.info("Product page loaded successfully");

        log.info("Fetching current URL after login");
        String url = driver.getCurrentUrl();
        log.info("Current URL is: {}", url);
        assertEquals(url, expectedurl(), "URL mismatch after login");
        log.info("Login flow passed, navigated to expected URL");
    }

    @Test(priority = 2)
    public void LoginFaiuretest() {
        log.info("Logging out from product page");
        productpage.logout();
        log.info("Successfully logged out, returning to login page");

        log.info("Attempting to login with invalid credentials");
        log.debug("Using username='Wrong_user' and password='Wrong_password'");
        loginPage.Login("Wrong_user", "Wrong_password");

        try {
            productpage = loginPage.clicklogin();
        } catch (LoginpageException e) {
            log.error("Login attempt failed due to exception: {}", e.getMessage(), e);
        }

        boolean loginFailed = loginPage.isloginfailed();
        log.info("Checking if error message is displayed for invalid login");
        assertTrue(loginFailed, "Error message is not displayed after failed login");
        log.info("Login failure validated successfully");
    }

    private String expectedurl() {
        return configbuilder.getproperty("app.uri") + "inventory.html";
    }
}
