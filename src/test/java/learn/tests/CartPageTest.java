package learn.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Cartpage;

public class CartPageTest extends BaseTest{

    Cartpage cartpage;
    @Test(priority = 1)
    public void checkitemincart(){
        productpage.additemtocart("Sauce Labs Backpack");
        productpage.additemtocart("Sauce Labs Bike Light");
        cartpage=productpage.gotocart();
        Assert.assertTrue(cartpage.isproductincart("Sauce Labs Backpack"),"Sauce Labs Backpack is not added to cart");
        Assert.assertTrue(cartpage.isproductincart("Sauce Labs Bike Light"),"Sauce Labs Bike Light is not added to cart");
        System.out.println("Product titled Sauce Labs Backpack is added to cart");
        System.out.println("Product titled Sauce Labs Bike Light is added to cart");
    }

    @Test(priority = 2)
    public void removeitemfromcart(){
        cartpage.removeproduct("Sauce Labs Backpack");
        Assert.assertFalse(cartpage.isproductincart("Sauce Labs Backpack"), "Product is still in cart");
        System.out.println("Product titled Sauce Labs Backpack is removed from cart");
        Assert.assertTrue(cartpage.isproductincart("Sauce Labs Bike Light"));
        System.out.println("Product titled Sauce Labs Bike Light is in cart");

    }
}
