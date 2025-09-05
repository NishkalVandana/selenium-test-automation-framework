package learn.tests;

import Exceptions.LoginpageException;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginFlow extends BaseTest {

    @Test(priority = 1)
    public void LoginFlow(){

        String logotxt=loginPage.getlogo();
        Assert.assertEquals(logotxt,"Swag Labs");

        //product page actions
        assertTrue(productpage.isproducpagedisplayed(),"Product page is not displayed");
        String url=driver.getCurrentUrl();
        assertEquals(url,expectedurl());
    }

    @Test(priority = 2)
    public void LoginFaiuretest(){
        productpage.logout();
        loginPage.Login("Wrong_user","Wrong_password");
        try {
            productpage=loginPage.clicklogin();
        }catch (LoginpageException e){
            //log statements
        }
        assertTrue(loginPage.isloginfailed(),"Error message is not dispayed");
    }

    private String expectedurl(){
        return configbuilder.getproperty("app.uri")+ "inventory.html";
    }


}
