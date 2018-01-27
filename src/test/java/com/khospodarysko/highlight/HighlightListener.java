package com.khospodarysko.highlight;

import com.khospodarysko.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.MDC;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HighlightListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        // Throwable throwable = iTestResult.getThrowable();
        // TODO: check what the issue was: assert or exception

        String testFullName = MDC.get("testFullName");
        List<By> copy = new ArrayList<>(LocatorCollector.testMethodBys.get(testFullName));

        WebDriver driver = ((BaseTest) iTestResult.getInstance()).driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", driver.findElement(copy.get(copy.size() - 1)));

        // try { Thread.sleep(10000); } catch (InterruptedException e) { // ignore} // for demo
    }
}
