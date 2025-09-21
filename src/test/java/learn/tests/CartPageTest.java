package learn.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Cartpage;

public class CartPageTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(CartPageTest.class);
    Cartpage cartpage;

    @Test(priority = 1)
    public void Additemtocart() {
        log.info("Adding multiple products to cart...");
        productpage.additemtocart("Sauce Labs Backpack");
        productpage.additemtocart("Sauce Labs Bike Light");

        cartpage = productpage.gotocart();
        log.info("Navigated to cart page");

        Assert.assertTrue(cartpage.isproductincart("Sauce Labs Backpack"), "Sauce Labs Backpack is not added to cart");
        log.info("Verified product titled 'Sauce Labs Backpack' is added to cart");

        Assert.assertFalse(cartpage.isproductincart("Sauce Labs Bike Light"), "Sauce Labs Bike Light is in cart");
        log.info("Verified product titled 'Sauce Labs Bike Light' is added to cart");
    }

    @Test(priority = 2)
    public void removeitemfromcart() {
        log.info("Attempting to remove 'Sauce Labs Backpack' from cart");
        cartpage.removeproduct("Sauce Labs Backpack");

        Assert.assertFalse(cartpage.isproductincart("Sauce Labs Backpack"), "Product is still in cart");
        log.info("Verified 'Sauce Labs Backpack' is successfully removed from cart");

        Assert.assertTrue(cartpage.isproductincart("Sauce Labs Bike Light"));
        log.info("Verified 'Sauce Labs Bike Light' is still in cart");
    }
}
