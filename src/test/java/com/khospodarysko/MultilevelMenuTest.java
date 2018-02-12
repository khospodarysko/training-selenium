package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Click on "Time" menu and check if printed time was the same as current system time.
 * UI, JS Executor
 */
public class MultilevelMenuTest extends BaseTest {
    @Test
    public void testMultileveMenu() {
        driver.get("file://" + absoluteFilePath("multilevel-menu"));
        WebElement menu = driver.findElement(By.cssSelector("#css-menu"));
        menu.click();

        waitForElement(driver, By.cssSelector("#link-gallery"));
        jsClick(driver, driver.findElement(By.cssSelector("#link-gallery")));


        waitForElement(driver, By.xpath("//a[text()='Time']"));
        jsClick(driver, driver.findElement(By.xpath("//a[text()='Time']")));

        WebElement currentTime = driver.findElement(By.cssSelector("#time"));
        String menuTime = currentTime.getText();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        String myTime = dtf.format(now);

        Assert.assertEquals(menuTime, myTime);
    }

    public static void jsClick(WebDriver driver, WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].click();", element);
    }

    public static void waitForElement(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
        catch (Exception e) {
            // ignore
        }
    }
}
