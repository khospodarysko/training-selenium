package com.khospodarysko;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * Click on button 5 times and assert that button's text is "Clicked 5"
 * Cover:
 * - implicit wait
 * - explicit wait
 * -
 */
public class ButtonTest extends BaseTest {
    @Test
    public void testExpectedCondition() {
        driver.get("file://" + absoluteFilePath("1. button"));

        // built-in
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("button")));
        // lambda
        new WebDriverWait(driver, 20).until(driver -> driver.findElement(By.tagName("button")).isDisplayed());
        // separate class
        new WebDriverWait(driver, 20).until(new VisibleByText("text"));
        // predicate
        new WebDriverWait(driver, 20).until(d -> dd("kostya"));

        IntStream.rangeClosed(1, 5).forEach(i -> driver.findElement(By.tagName("button")).click());

        Assertions.assertThat(driver.findElement(By.tagName("button")).getText())
            .isEqualTo("Clicked 5");
    }

    private Predicate<WebDriver> dd(String text) {
        return driver -> {
            WebElement element = driver.findElement(By.xpath("//*[text()='" + text + "']"));
            return element.isDisplayed();
        };
    }
}

class VisibleByText implements ExpectedCondition<WebElement> {
    private final String text;

    public VisibleByText(String text) {
        this.text = text;
    }

    @Nullable
    @Override
    public WebElement apply(@Nullable WebDriver driver) {
        WebElement element = driver.findElement(By.xpath("//*[text()='" + text + "']"));
        return element.isDisplayed() ? element : null;
    }
}
