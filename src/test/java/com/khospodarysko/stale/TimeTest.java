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
    public void testExpectedCondition() {
        driver.get("file://" + absoluteFilePath("time"));
    }

    @Test
    public void testExpectedConditionFailed() {
        driver.get("file://" + absoluteFilePath("time"));
    }

    @Test
    public void testExpectedConditionFailedBuiltInCondition() {
        driver.get("file://" + absoluteFilePath("time"));
    }

    @Test
    public void testExpectedConditionFailedCustomClassCondition1() {
        driver.get("file://" + absoluteFilePath("time"));
    }

    @Test
    public void testExpectedConditionFailedCustomClassCondition2() {
        driver.get("file://" + absoluteFilePath("time"));
    }

    @Test
    public void testExpectedConditionFailedCustomAnonymousCondition() {
        driver.get("file://" + absoluteFilePath("time"));
    }

    @Test
    public void testExpectedConditionFailedCustomAnonymousConditionWithMessage() {
        driver.get("file://" + absoluteFilePath("time"));
    }
}