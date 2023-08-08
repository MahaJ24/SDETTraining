	package HCLTECH_Usecase1;
	
	import java.awt.AWTException;
	import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.List;
	import java.util.Properties;
	
	import javax.imageio.ImageIO;
	
	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.io.FileHandler;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
	import ru.yandex.qatools.ashot.comparison.ImageDiff;
	import ru.yandex.qatools.ashot.comparison.ImageDiffer;
	
	public class Usecase1 {
		
		WebDriver driver;
		
		ExtentReports extentreports;
	    ExtentHtmlReporter htmlReport;

	    ExtentTest testcase;

		
		@BeforeTest
		
		public void launch()
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.get("https://www.hcltech.com/");
			driver.manage().window().maximize();
		
		
			
	        extentreports=new ExtentReports();
	        htmlReport =  new ExtentHtmlReporter("ExtentReport.html");
	        extentreports.attachReporter(htmlReport);


	    }
		
		@Test(priority=0)
	
		public void display_menus() throws IOException
		{
			List<WebElement> menu_items= driver.findElements(By.xpath("//*[@id=\"block-mainnavigationbt\"]/li/a"));
			int size= menu_items.size();
			System.out.println("Total no. of. menus in the header is: "+ size);
	
			for (int i=1; i<size; i++)
			{
		 	String menu_name = driver.findElement(By.xpath("//*[@id=\"block-mainnavigationbt\"]/li["+i+"]/a")).getText();
		 	System.out.println(menu_name);
		 	
			}
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\mahalakshmi.jagad\\OneDrive - HCL Technologies Ltd\\Pictures\\Screenshots\\HCLHomeCapture.png");
		FileUtils.copyFile(src, dest);
		
		BufferedImage expectedImage = ImageIO.read(new File("C:\\Users\\mahalakshmi.jagad\\OneDrive - HCL Technologies Ltd\\Pictures\\Screenshots\\ManualCapture.png"));
	
	    BufferedImage actualImage = ImageIO.read(new File("C:\\\\Users\\\\mahalakshmi.jagad\\\\OneDrive - HCL Technologies Ltd\\\\Pictures\\\\Screenshots\\\\HCLHomeCapture.png"));
	
	    ImageDiffer imgDiff = new ImageDiffer();
	    ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
	    if(diff.hasDiff()==true)
	    {
	     System.out.println("Images are Not Same");
	    }
	    else {
	     System.out.println("Images are Same");
	    }
		
			
		}
		
		
		@Test(priority=1)
		public void contactus() throws IOException, AWTException
		{
			driver.navigate().to("https://www.hcltech.com/contact-us/customer");
			driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
			FileInputStream f1 = new FileInputStream("C:\\Users\\mahalakshmi.jagad\\eclipse-workspace\\SDET\\src\\test\\java\\HCLTECH_Usecase1\\Config.properties");
			Properties prop= new Properties();
			prop.load(f1);
			String name= prop.getProperty("fullname");
			String email= prop.getProperty("emailaddr");
			String org= prop.getProperty("organisation");
	
			String ph= prop.getProperty("phone");
			String help= prop.getProperty("help");
			
			driver.findElement(By.name("full_name")).sendKeys(name);
			driver.findElement(By.name("email_address")).sendKeys(email);
			driver.findElement(By.id("edit-organization")).sendKeys(org);
			driver.findElement(By.id("edit-phone")).sendKeys(ph);
			
			Select country= new Select(driver.findElement(By.name("country")));
			country.selectByVisibleText("India");
			Select relation= new Select(driver.findElement(By.name("query_type")));
			relation.selectByVisibleText("Request for Services");
			Select job = new Select(driver.findElement(By.name("designation")));
			job.selectByVisibleText("Manager");
			driver.findElement(By.id("edit-message-comments")).sendKeys(help);
			driver.findElement(By.id("edit-upload-multifile--label")).click();
			String filepath = "C:\\Users\\mahalakshmi.jagad\\OneDrive - HCL Technologies Ltd\\Documents\\handson details.txt";
			Robot rb = new Robot();
			rb.delay(5000);
			StringSelection ss = new StringSelection("C:\\Users\\mahalakshmi.jagad\\OneDrive - HCL Technologies Ltd\\Documents\\handson details.txt");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			
			rb.delay(2000);
			
			rb.keyRelease(KeyEvent.VK_CONTROL);
			rb.keyRelease(KeyEvent.VK_V);
			
			rb.delay(3000);
			
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
			
			driver.findElement(By.id("edit-privacy-policy")).click();
				

	        testcase=extentreports.createTest("validate and attach the file to contact us page");

	    

	        testcase.log(Status.INFO, "Fields of Contact us page is validated");
        
	        
	        TakesScreenshot ts1=(TakesScreenshot) driver;
	        File source=ts1.getScreenshotAs(OutputType.FILE);
	        File destn=new File("C:\\Users\\mahalakshmi.jagad\\OneDrive - HCL Technologies Ltd\\Pictures\\Screenshots\\contactus.png");
	            FileUtils.copyFile(source, destn);
	        testcase.addScreenCaptureFromPath("C:\\Users\\mahalakshmi.jagad\\OneDrive - HCL Technologies Ltd\\Pictures\\Screenshots\\contactus.png");
	        extentreports.flush();

	        }
		
	}
