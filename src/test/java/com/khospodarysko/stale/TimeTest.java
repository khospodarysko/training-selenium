package com.khospodarysko.stale;

import com.khospodarysko.BaseTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Task:
 * print different time 5 times that end on 0, like
 * 11:36:40
 * 11:36:50
 * 11:37:00
 * 11:37:10
 * 11:37:20
 *
 * without Thread.sleep
 */

public class TimeTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(TimeTest.class);

    @Test
    public void testExpectedCondition() throws InterruptedException {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/time.html");
    }

    @Test
    public void testExpectedConditionFailed() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/time.html");
    }

    @Test
    public void testExpectedConditionFailedBuiltInCondition() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/time.html");
    }

    @Test
    public void testExpectedConditionFailedCustomClassCondition1() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/time.html");
    }

    @Test
    public void testExpectedConditionFailedCustomClassCondition2() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/time.html");
    }

    @Test
    public void testExpectedConditionFailedCustomAnonymousCondition() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/time.html");
    }

    @Test
    public void testExpectedConditionFailedCustomAnonymousConditionWithMessage() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/time.html");
    }
}