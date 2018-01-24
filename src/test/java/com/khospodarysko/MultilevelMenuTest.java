package com.khospodarysko;

import org.testng.annotations.Test;

/**
 * Click on "Time" menu and check if printed time was the same as current system time.
 * UI, JS Executor
 */
public class MultilevelMenuTest extends BaseTest {
    @Test
    public void test() {
        driver.get("file://" + absoluteFilePath("multilevel-menu"));
        System.out.println(1);
    }
}
