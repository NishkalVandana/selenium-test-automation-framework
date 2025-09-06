package learn.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Logoutest extends BaseTest {

    @Test
    public void testlogout(){

        Assert.assertTrue(productpage.isproducpagedisplayed(), "Product page is not dispayed");
        productpage.logout();
        Assert.assertTrue(loginPage.isloginpagedisplayed());
    }
}
