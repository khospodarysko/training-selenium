package com.khospodarysko.stale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class HasTimeSeparator implements ExpectedCondition<Boolean> {
    private String separator;

    public HasTimeSeparator() {
        this(":");
    }

    public HasTimeSeparator(String separator) {
        this.separator = separator;
    }

    public Boolean apply(WebDriver driver) {
        return driver.findElement(By.id("child")).getText().contains(separator);
    }
}