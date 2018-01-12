package com.khospodarysko;

import org.testng.annotations.Test;

/**
 * Click on button 5 times and assert that button's text is "Clicked 5"
 */
public class ButtonTest extends BaseTest {
    @Test
    public void testExpectedCondition() {
        driver.get("file://" + absolutePath("button"));
    }
}
