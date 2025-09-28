package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	

	public void onStart(ITestContext context) 
	 {
		//System.out.println("Before Starting");
		SimpleDateFormat dateFormat= new SimpleDateFormat("ddMMyyyy_HHmmss");		
		String formattedDate = dateFormat.format(new Date());
		String myFileName = "Report_" + formattedDate+ ".html";	
		
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+myFileName);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Computer name", "localhost");
		extent.setSystemInfo("Environment", "SIT");
		extent.setSystemInfo("os", "Windows10");
		extent.setSystemInfo("Browser name", "Chrome");
		
			
	  }
	
	 public void onTestStart(ITestResult result) 
	 {
		 //System.out.println("Test execution is started");
		 
		 
	  }
		
	 public  void onTestSuccess(ITestResult result) 
	 {
		 
		 test = extent.createTest(result.getName());
		 test.log(Status.PASS, "Test case PASSED is: "+result.getName());		 
	  }

		  
	 public  void onTestFailure(ITestResult result) 
	 {
		 test = extent.createTest(result.getName());
		 test.log(Status.FAIL, "Test case FAILED is: "+result.getName());
		 test.log(Status.FAIL, "Test case FAILED cause is: "+result.getThrowable());
	  }

	 
	 public	 void onTestSkipped(ITestResult result) 
	 {
		 test = extent.createTest(result.getName());
		 test.log(Status.SKIP, "Test case SKIPPED is: "+result.getName());	
		 
		 //System.out.println("Test is Skipped");  
	  }
				
	 
	 public void onFinish(ITestContext context) 
	 {
		 extent.flush();
		 //System.out.println("Before Ending");	
	  }
	
	
}
