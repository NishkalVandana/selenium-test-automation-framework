package learn.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Productpagetest extends BaseTest {
    private static final Logger log = LogManager.getLogger(Productpagetest.class);

    @Test(priority = 1)
    public void testproductdetails() {
        log.info("===== Starting test: testproductdetails =====");

        log.info("Verifying if Product Page is displayed");
        Assert.assertTrue(productpage.isproducpagedisplayed(),
                "Product page is not displayed after login");

        log.info("Verifying if product title 'Sauce Labs Bike Light' is displayed");
        Assert.assertTrue(productpage.isproducttitledisplayed("Sauce Labs Bike Light"),
                "Product title is not displayed");

        log.info("Verifying if product price for 'Sauce Labs Bike Light' is displayed");
        Assert.assertTrue(productpage.isproductpricedisplayed("Sauce Labs Bike Light"),
                "Product price is not displayed");

        log.info("===== Completed test: testproductdetails =====");
    }

    @Test(priority = 2)
    public void addproducttocart() {
        log.info("===== Starting test: addproducttocart =====");

        log.info("Adding product 'Sauce Labs Bike Light' to cart");
        productpage.additemtocart("Sauce Labs Bike Light");

        log.info("Verifying product 'Sauce Labs Bike Light' is added to cart");
        Assert.assertTrue(productpage.isproductincart("Sauce Labs Bike Light"),
                "Product was not found in the cart after adding");

        log.info("===== Completed test: addproducttocart =====");
    }
}
