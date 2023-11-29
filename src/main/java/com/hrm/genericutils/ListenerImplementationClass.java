package com.hrm.genericutils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementationClass implements ITestListener {
	ExtentReports report;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		test=report.createTest(methodName);
		Reporter.log(methodName+"---Testscript execution starts from here---");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		test.log(Status.PASS,methodName+"---> PASSED");
		Reporter.log(methodName+"---Testscript excuted successfully---");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String failedTestName = result.getMethod().getMethodName();
		String FScript=failedTestName+new JavaUtils().getSystemDate();
		test.addScreenCaptureFromPath(FScript);
		try {
			WebDriverUtils.getScreenShot(BaseClass.ssDriver, failedTestName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(Status.FAIL, result.getThrowable());
		test.log(Status.FAIL, FScript+"--->FAILED");
		Reporter.log(FScript+"--->Testscript execution failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName+"---SKIPPED---");
		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(methodName+"---Test execuion skipped---");
	}
	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter htmlReport= new ExtentSparkReporter("./extentreport/report.html");
		htmlReport.config().setDocumentTitle("MSHARK_HRM");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("TEST_EXECUTION_REPORT");
		
		report=new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base-Browser","chrome");
		report.setSystemInfo("url","http://localhost:8888");
		report.setSystemInfo("Reporter Name","Hemanth");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
}
