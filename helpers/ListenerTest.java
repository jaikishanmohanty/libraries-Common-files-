package helpers;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import browsers.BaseTest;
import browsers.BrowserActions;


/**Listener
 * @author priyankag
 * @throws Exception 
 */
public class ListenerTest  implements ITestListener,ISuiteListener, IInvokedMethodListener{
	WebDriver driver = null;
	long startTime;
	long endTime;
	long duration;

	// When Test case get failed, this method is called.
	/*
	 * @Override
	 * 
	 * public void onTestFailure(ITestResult Result) 
	 * {
	 * 	String url = System.getProperty("user.dir") + "/../test-output/screenshot/";
	 *  BrowserActions actions;
	 * Object currentClass = Result.getInstance(); WebDriver driver = ((BaseTest)
	 * currentClass).getDriver(); String
	 * methodName=Result.getName().toString().trim(); if (driver != null) { actions
	 * = new BrowserActions(driver); actions.captureScreenShot(driver,url,
	 * methodName); } }
	 * 
	 */
	  @Override 
	  public void onTestFailure(ITestResult Result)
	  {
	  BrowserActions actions;
	  Object currentClass = Result.getInstance();
	  WebDriver driver = ((BaseTest) currentClass).getDriver(); 
	  String methodName = Result.getName();
	  System.out.println(methodName); 
	  actions = new BrowserActions(driver); 
	  try {
	  String screenshotPath =actions.getScreenshot(methodName, driver);
	  System.out.println("Screenshot taken"); 
	  String path = "<img src=\"file://" +screenshotPath + "\" alt=\"\"/>";	  
	  System.out.println(screenshotPath+" and path - "+path);
	  Reporter.log("Capcher screenshot path is "+path); 
	  } catch (Exception e) {
	  System.out.println("Exception while takescreenshot "+e.getMessage()); } //
	 // printTestResults(arg0);
	  
	  }
	 
	@Override
	public void onFinish(ITestContext Result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext Result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		// TODO Auto-generated method stub
		
	}



	// When Test case get Skipped, this method is called.	
	@Override
	public void onTestSkipped(ITestResult Result) {
		 System.out.println("The name of the testcase Skipped is :"+Result.getName());
	}

	// When Test case get Started, this method is called.
	@Override
	public void onTestStart(ITestResult Result) {
		System.out.println(Result.getName()+" test case started");
		System.out.println("Test Started�"+Result.getStartMillis());
		startTime = Result.getStartMillis();
	}

	// When Test case get passed, this method is called.
	@Override
	public void onTestSuccess(ITestResult Result) {
		System.out.println("The name of the testcase passed is :"+Result.getName());
		System.out.println("Test Success. "+Result.getEndMillis());
		endTime = Result.getEndMillis();
	}
	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}
	


}
