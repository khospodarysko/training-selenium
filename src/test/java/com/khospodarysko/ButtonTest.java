package com.khospodarysko;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

/**
 * Click on button 5 times and assert that button's text is "Clicked 5"
 */
public class ButtonTest extends BaseTest {
    @Test
    public void testExpectedCondition() {
        driver.get("file://" + absoluteFilePath("button"));

        new WebDriverWait(driver, 20).until(driver -> driver.findElement(By.tagName("button")).isDisplayed());

        IntStream.rangeClosed(1, 5).forEach(i -> driver.findElement(By.tagName("button")).click());

        Assertions.assertThat(driver.findElement(By.tagName("button")).getText())
            .isEqualTo("Clicked 5");
    }
}
