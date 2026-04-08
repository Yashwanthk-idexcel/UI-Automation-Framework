package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtil;
import com.utility.ExtentReporterUtil;
import com.utility.LoggerUtil;

public class TestListener implements ITestListener {
	Logger logger = LoggerUtil.getLogger(this.getClass());

	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));

		ExtentReporterUtil.createExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " " + "Passed.");

		ExtentReporterUtil.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED!!");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + "Failed.");
		logger.error(result.getThrowable().getMessage());

		ExtentReporterUtil.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED!!");
		ExtentReporterUtil.getTest().log(Status.FAIL, result.getThrowable().getMessage());

		Object testClassRef = result.getInstance();
		BrowserUtil browserUtil = ((TestBase)testClassRef).getBrowserInstance();
		String path = browserUtil.takeScreenshot(result.getMethod().getMethodName());
		
		ExtentReporterUtil.getTest().addScreenCaptureFromPath(path);
		
	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + "Skipped.");

		ExtentReporterUtil.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED!!");
	}

	public void onStart(ITestContext context) {
		logger.info("Test Suite Started");
		ExtentReporterUtil.setupSparkReporter("report.html");
	}

	public void onFinish(ITestContext context) {
		logger.info("Test Suite Ended");
		ExtentReporterUtil.flushReport();
	}

}
