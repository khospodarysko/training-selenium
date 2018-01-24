package com.khospodarysko;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Scroll to button 50 and click on it.
 * Just click on button 50.
 * Also scroll moved entire DOM, but location of element is not changed as "viewport" is moved, i.e our look on page,
 * but no elements in the page, that is why element's location is not changed.
 */
public class DragBelowTest extends BaseTest {
    @Test
    public void testBrokenScroll() {
        driver.get("file://" + absoluteFilePath("drag-below"));

        Assertions.assertThat(driver.findElement(By.id("clicked")).getText())
            .isEqualTo("Button 50");
    }

    @Test
    public void testFixedScroll() {
        driver.get("file://" + absoluteFilePath("drag-below"));

        Assertions.assertThat(driver.findElement(By.id("clicked")).getText())
            .isEqualTo("Button 200");
    }
}