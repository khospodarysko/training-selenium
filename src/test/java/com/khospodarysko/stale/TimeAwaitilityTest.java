package com.khospodarysko.stale;

import com.khospodarysko.BaseTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TimeAwaitilityTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(TimeAwaitilityTest.class);

    @Test
    public void testAwaitility() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/time.html");
    }
}