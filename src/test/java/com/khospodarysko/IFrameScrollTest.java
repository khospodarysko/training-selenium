package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

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
