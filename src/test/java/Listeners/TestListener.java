package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import learn.tests.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getinstance();  // get ExtentReports object
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();  // each test has its own ExtentTest

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest); // bind this ExtentTest to current thread
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.driver;
        if (driver != null) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File src = ts.getScreenshotAs(OutputType.FILE);

                // Save screenshot inside project/reports/screenshots
                String screenshotPath = System.getProperty("user.dir")
                        + "/reports/screenshots/" + result.getName() + ".png";

                File dest = new File(screenshotPath);
                dest.getParentFile().mkdirs();
                Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Use relative path for report
                String relativePath = "screenshots/" + result.getName() + ".png";
                test.get().addScreenCaptureFromPath(relativePath);

            } catch (Exception e) {
                test.get().log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
            }
        }
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
        extent.flush(); // generate ExtentReport.html
    }
}
