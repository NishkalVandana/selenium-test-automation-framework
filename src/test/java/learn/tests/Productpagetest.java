package learn.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Productpagetest extends BaseTest{
    @Test(priority = 1)
    public void testproductdetails(){
        Assert.assertTrue(productpage.isproducpagedisplayed());
        Assert.assertTrue(productpage.isproducttitledisplayed("Sauce Labs Bike Light"),
                "Product tite is not displayed");
        Assert.assertTrue(productpage.isproductpricedisplayed("Sauce Labs Bike Light"),
                "Product price is not displayed");
    }
    @Test(priority = 2)
    public void addproducttocart(){
        productpage.additemtocart("Sauce Labs Bike Light");
        Assert.assertTrue(productpage.isproductincart("Sauce Labs Bike Light"));
    }
}
