package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

/**
 * Scroll inside frame scroll element to the top of the main page.
 * Also it scrolls entire page.
 * But simply doing some action scroll only element inside iframe just right.
 */
public class IFrameScrollTest extends BaseTest {
    @Test
    public void test() {
        driver.get("http://toolsqa.com/iframe-practice-page/");

        driver.switchTo().frame("IF1");

        // driver.findElement(By.id("datepicker")).sendKeys("kostya is here");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()",
            driver.findElement(By.id("datepicker")));

        driver.findElement(By.id("datepicker")).sendKeys("kostya is here");
    }
}
