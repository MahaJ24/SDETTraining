//package Amazon;
//
// import java.io.File;
//import java.io.IOException;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.io.FileHandler;
//import org.testng.annotations.Test;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
// 
//
// 
//
//public class Extentreportdemo {
//
//    WebDriver driver;
//    ExtentReports extentreports;
//    ExtentSparkReporter htmlreport;
//
//    ExtentTest testcase;
//
//    @Test(priority=0)
//    public void before_class() {
//        extentreports=new ExtentReports();
//        htmlreport =  new ExtentSparkReporter("ExtentReport.html");
//        extentreports.attachReporter(htmlreport);
//
//
//    }
//    @Test(priority=1)
//    public void test_title() throws IOException {
//
//
//        WebDriverManager.chromedriver().setup();
//        driver=new ChromeDriver();
//        driver.get("https://demo.openmrs.org/openmrs/login.htm");
//
//        testcase=extentreports.createTest("Test the title of the OPENMRS application");
//
//    //    driver.get("https://demo.openmrs.org/openmrs/login.htm");
//
//        testcase.log(Status.INFO, "application opened in the browser");
//
//        String exp_title="OPENMRS login";
//        testcase.log(Status.INFO, "got the expected title from the code");
//
//        String act_title = driver.getTitle();
//        testcase.log(Status.INFO, "got the actual title from the application");
//
//        if(exp_title.equalsIgnoreCase(act_title)) {
//            testcase.log(Status.PASS, "title matched");
//        }else
//        {
//            testcase.log(Status.FAIL, "Title not matched");
//        TakesScreenshot ts=(TakesScreenshot) driver;
//        File source=ts.getScreenshotAs(OutputType.FILE);
//        File dest=new File("OPENMRS.png");
//            FileHandler.copy(source, dest);
//        testcase.addScreenCaptureFromPath("OPENMRS.png");
//
//        }
//
//
//    }
//    @Test(priority=2)
//    public void after_class() {
//        driver.quit();
//        extentreports.flush();
//
//    }
//
// 
//
//}