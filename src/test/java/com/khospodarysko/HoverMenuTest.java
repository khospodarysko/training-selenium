package com.khospodarysko;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class HoverMenuTest extends BaseTest {
    @Test(invocationCount = 10)
    public void testExpectedCondition() throws InterruptedException {
        driver.get("file://" + absoluteFilePath("hover-menu"));
        TimeUnit.SECONDS.sleep(2);

        Page page = new Page(driver);
        page.button.click();

        LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

        Actions hover = new Actions(driver);
        hover.moveToElement(page.hover1).moveToElement(page.hover2).click(page.hover3).build().perform();

        // Assertions.assertThat(page.getTime()).isEqualTo(now);
        Assertions.assertThat(page.getTime()).isEqualToIgnoringNanos(now);
    }

    private static class Page {
        @FindBy(id = "dLabel")
        public WebElement button;

        @FindBy(xpath = "//a[text()='Hover me for more options']")
        public WebElement hover1;

        @FindBy(xpath = "//a[text()='Even More..']")
        public WebElement hover2;

        @FindBy(xpath = "//a[text()='2nd 3rd level']")
        public WebElement hover3;

        @FindBy(id = "text")
        private WebElement text;

        public Page(WebDriver driver) {
            PageFactory.initElements(driver, this);
        }

        public LocalTime getTime() {
            return LocalTime.parse(text.getText(), DateTimeFormatter.ofPattern("h:mm:ss a"));
        }
    }
}
