package com.testcomponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.resources.ExtentReports_NG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;

	ExtentReports extent = ExtentReports_NG.getReportObject();// classname.methodname

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();// Unique thread id to avoid concurrent issue

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());

		extentTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test passed");

	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());// useful to throw failure error
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());// giving
																													// life
																													// to
																													// driver
																													// from
																													// BaseTest
		}

		catch (Exception e) {

			e.printStackTrace();
		}

		String filePath = null;

		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (Throwable e) {

			e.printStackTrace();
		}

		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

		extentTest.get().log(Status.FAIL, "Test Failed");// optional one so we can ignore it

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
