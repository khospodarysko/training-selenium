package com.khospodarysko;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

        button.click();
        button.click();
        button.click();
        button.click();
        button.click();

        Assert.assertEquals(button.getText(), "Clicked 5");
    }

    public static Boolean waitElementUntilText(WebDriver driver, WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return element.getText().equals(text);
                } catch (StaleElementReferenceException | NoSuchElementException ex) {
                    return false;
                }
            }
        });
    }
}
