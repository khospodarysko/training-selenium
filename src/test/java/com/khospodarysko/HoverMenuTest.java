package com.khospodarysko;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
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
    @Test(invocationCount = 1)
    public void testExpectedCondition() throws InterruptedException {
        driver.get("file://" + absoluteFilePath("hover-menu"));
        TimeUnit.SECONDS.sleep(2);

        Page page = new Page(driver);
        page.button.click();

        LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

        // Actions hover = new Actions(driver);
        // hover.moveToElement(page.hover1).moveToElement(page.hover2).click(page.hover3).build().perform();

        page.openMenuItem("Hover me for more options", "Even More..", "final level");

        // Assertions.assertThat(page.getTime()).isEqualTo(now);
        Assertions.assertThat(page.getTime()).isEqualToIgnoringNanos(now.plusSeconds(2));
    }

    private static class Page {
        WebDriver driver;

        @FindBy(id = "dLabel")
        public WebElement button;

        @FindBy(xpath = "//a[text()='Hover me for more options']")
        public WebElement hover1;

        @FindBy(xpath = "//a[text()='Even More..']")
        public WebElement hover2;

        @FindBy(xpath = "//a[text()='final level']")
        public WebElement hover3;

        @FindBy(id = "text")
        private WebElement text;

        public Page(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public LocalTime getTime() {
            return LocalTime.parse(text.getText(), DateTimeFormatter.ofPattern("h:mm:ss a"));
        }

        public void openMenuItem(String... items) throws InterruptedException {
            Actions action = new Actions(driver);
            WebElement first = null;
            for (int i = 0; i < items.length - 1; i++) {
                if (i == 0) {
                    first = driver.findElement(By.xpath("//*[text()='" + items[0] + "']"));
                    action.moveToElement(first).perform();
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    first = first.findElement(By.xpath(".././/*[text()='" + items[i] + "']"));
                    action.moveToElement(first).perform();
                    TimeUnit.SECONDS.sleep(1);
                }

            }

            WebElement element = first.findElement(By.xpath(".././/*[text()='" + items[items.length - 1] + "']"));
            action.click(element).perform();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
