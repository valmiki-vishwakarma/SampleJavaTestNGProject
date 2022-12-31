package test;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestCase1 {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest
	public void setReport() {
		htmlReporter = new ExtentHtmlReporter("./report/extent.html");
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setDocumentTitle("VV Extent Report 14Sep2022");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Automation Expert ", "Valmiki Vishwakarma");
		extent.setSystemInfo("Organisation", "Google");
		extent.setSystemInfo("Build No", "510");

	}

	@AfterTest
	public void endReport() {

		extent.flush();
	}
	
	/*
	 * 
	 * Pass ,Fail, Skip
	 * 
	 * 
	 */

	
	@Test
	public void doLogin() {
		 test= extent.createTest("Login Test");
		 System.out.println("Executing login test");
	}
	
	

	@Test
	public void isSkip() {
		 test= extent.createTest("Skip Test");
		 System.out.println("Executing skip test");
		 throw new SkipException("Skipping test..!!");
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if(result.getStatus() ==ITestResult.FAILURE) {
			
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			
			String methodName=result.getMethod().getMethodName();
			String logText="<b>"+ " TEST CASE: - "+methodName.toUpperCase()+"</b>";
			Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			test.pass(m);
			
		}
	}
	
	
	
	
	
	
	
}
