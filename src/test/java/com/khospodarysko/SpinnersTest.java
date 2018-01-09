package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

public class SpinnersTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SpinnersTest.class);

    @Test
    public void test() {
        driver.get("file:///Users/khospodarysko/projects/training-selenium/src/main/resources/spinners.html");

        logger.info("{}", areSpinnersLoaded());
    }

    public boolean areSpinnersLoaded() {
        By smallSpinnerBy = By.cssSelector(".spinner-small");
        By largeSpinnerBy = By.cssSelector(".spinner-large");

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.ignoring(StaleElementReferenceException.class);

        try {
            return wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver inDriver) {
                    // here inDriver and driver reference the same instance

                    List<WebElement> smallSpinners = driver.findElements(smallSpinnerBy);
                    int smalls = smallSpinners.size();
                    boolean isAnySmallSpinnerDisplayed = smallSpinners.stream().anyMatch(WebElement::isDisplayed);

                    List<WebElement> largeSpinners = driver.findElements(largeSpinnerBy);
                    int larges = largeSpinners.size();
                    boolean isAnyLargeSpinnerDisplayed = largeSpinners.stream().anyMatch(WebElement::isDisplayed);

                    logger.info("small {}, large {}", smalls, larges);

                    // both should not be displayed
                    return !isAnySmallSpinnerDisplayed && !isAnyLargeSpinnerDisplayed;
                }

                @Override
                public String toString() {
                    return "small and large spinners to be loaded";
                }
            });
        } catch (TimeoutException ex) {
            logger.warn("waited for specified timeout, means some spinners are still displayed");
            return false;
        }
    }
}
