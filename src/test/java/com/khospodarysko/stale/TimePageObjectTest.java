package com.khospodarysko.stale;

import com.khospodarysko.BaseTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TimePageObjectTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(TimePageObjectTest.class);

    @Test
    public void testExpectedCondition() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/time.html");

        ChildPage page = new ChildPage(driver);
    }
}