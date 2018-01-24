package com.khospodarysko;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Find only failed tests and print them along with the class they relate to.
 */
public class JenkinsTest extends BaseTest {
    @BeforeMethod
    public void openSuite() {
        driver.get("file://" + absoluteFilePath("jenkins-small"));
    }

    @Test
    public void testUnfoldingSpeed_Selenium() {
    }

    @Test
    public void testUnfoldingSpeed_Actions() {
    }

    @Test
    public void testUnfoldingSpeed_JS() {
    }
}
