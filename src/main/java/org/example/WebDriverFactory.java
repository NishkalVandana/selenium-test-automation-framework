package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

    private static String remote = System.getProperty("remote");
    private static final String HUB_URL = "http://localhost:4444/wd/hub";

    private WebDriverFactory() {} // private constructor to prevent instantiation

    public static WebDriver createDriver(Browser browser) {
        WebDriver driver;
        if ("true".equalsIgnoreCase(remote)) {
            driver = getRemoteWebDriver(browser);
        } else {
            driver = getLocalWebDriver(browser);
        }
        return driver;
    }

    public static WebDriver getLocalWebDriver(Browser browser) {
        WebDriver driver;
        switch (browser) {
            case CHROME -> driver = new ChromeDriver();
            case EDGE -> driver = new EdgeDriver();
            case FIREFOX -> driver = new FirefoxDriver();
            default -> driver = new ChromeDriver();
        }
        return driver;
    }

    public static WebDriver getRemoteWebDriver(Browser browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser.getBrowserName());

        try {
            return new RemoteWebDriver(new URL(HUB_URL), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid hub URL: " + HUB_URL, e);
        }
    }
}
