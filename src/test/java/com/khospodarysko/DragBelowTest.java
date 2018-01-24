package com.khospodarysko;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DragBelowTest extends BaseTest {
    @Test
    public void testBrokenScroll() {
        driver.get("file://" + absoluteFilePath("drag-below"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",
            driver.findElement(By.xpath("//*[text()='Button 50']")));

        driver.findElement(By.xpath("//*[text()='Button 50']")).click();

        Assertions.assertThat(driver.findElement(By.id("clicked")).getText())
            .isEqualTo("Button 50");
    }

    @Test
    public void testFixedScroll() {
        driver.get("file://" + absoluteFilePath("drag-below"));

        scrollTo(By.id("news"), By.xpath("//*[text()='Button 200']"));
        driver.findElement(By.xpath("//*[text()='Button 200']")).click();

        Assertions.assertThat(driver.findElement(By.id("clicked")).getText())
            .isEqualTo("Button 200");
    }

    private void scrollTo(By relativeLocator, By scrollToLocator) {
        WebElement relative = driver.findElement(relativeLocator);
        WebElement scrollTo = driver.findElement(scrollToLocator);

        int extraOffset = 10;

        ((JavascriptExecutor) driver).executeScript(
            String.format("window.scrollBy(0, %d - %d - %d);",
                scrollTo.getLocation().getY(),
                relative.getSize().getHeight(),
                extraOffset
            )
        );
    }
}