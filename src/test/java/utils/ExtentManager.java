package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports getinstance(){
        String path=System.getProperty("user.dir")+"/reports/ExtentReport.html";
        if(extent==null){
            extent=new ExtentReports();
            ExtentSparkReporter sparkReporter=new ExtentSparkReporter(path);
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Project", "Automation Framework");
            extent.setSystemInfo("Tester", "Nishkal");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
            return extent;
    }
}
