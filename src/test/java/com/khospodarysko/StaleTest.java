package com.khospodarysko;

import static org.awaitility.Awaitility.await;

import com.google.common.base.Joiner;

import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

public class StaleTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(StaleTest.class);

    // TODO: unsuccessful tests

    @Test
    public void testExpectedCondition() throws InterruptedException {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/stale.html");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until((ExpectedCondition<Boolean>) driver -> driver.findElement(By.id("child")).getText().contains(":"));

        for (int i = 0; i < 5; i++) {
            logger.info("{}", driver.findElement(By.id("child")).getText());
            Thread.sleep(3000);
        }
    }

    @Test
    public void testExpectedConditionFailed() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/stale.html");

        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until((ExpectedCondition<Boolean>) driver -> driver.findElement(By.id("child")).getText().contains(":"));
    }

    @Test
    public void testExpectedConditionFailedBuiltInCondition() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/stale.html");

        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("child"), ":"));
    }

    @Test
    public void testExpectedConditionFailedCustomClassCondition() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/stale.html");

        class HasTimeSeparator implements ExpectedCondition<Boolean> {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.id("child")).getText().contains(":");
            }
        }

        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(new HasTimeSeparator());
    }

    @Test
    public void testExpectedConditionFailedCustomAnonymousCondition() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/stale.html");

        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.id("child")).getText().contains(":");
            }
        });
    }

    @Test
    public void testExpectedConditionFailedCustomAnonymousConditionWithMessage() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/stale.html");

        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.id("child")).getText().contains(":");
            }

            @Override
            public String toString() {
                return "what was expected";
            }
        });
    }

    @Test
    public void testAwaitility() throws InterruptedException {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/stale.html");

        await("message text")
            .atMost(5, TimeUnit.SECONDS)
            .until(() -> driver.findElement(By.id("child")).getText().contains(":"));

        for (int i = 0; i < 5; i++) {
            logger.info("{}", driver.findElement(By.id("child")).getText());
            Thread.sleep(3000);
        }
    }
}