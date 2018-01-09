package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class StaleTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(StaleTest.class);

    @Test
    public void test() throws InterruptedException {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/stale.html");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until((ExpectedCondition<Boolean>) driver -> driver.findElement(By.id("child")).getText().contains(":"));

        for (int i = 0; i < 5; i++) {
            logger.info("{}", driver.findElement(By.id("child")).getText());
            Thread.sleep(3000);
        }
    }
}
