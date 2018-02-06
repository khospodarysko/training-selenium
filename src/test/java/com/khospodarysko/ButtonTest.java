package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Click on button 5 times and assert that button's text is "Clicked 5"
 */
public class ButtonTest extends BaseTest {

    @Test
    public void testExpectedCondition() {
        driver.get("file://" + absoluteFilePath("button"));
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.hide")));
        } catch (Exception e) {
            // ignore
        }
        WebElement button = driver.findElement(By.cssSelector("button.hide"));
        String butt = "";
        while(!(butt.equals("Clicked 5"))) {
            button.click();
            butt = button.getText();
        }
        Assert.assertEquals(button.getText(), "Clicked 5");
    }
}
