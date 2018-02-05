package com.khospodarysko;

import org.assertj.core.util.Compatibility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Wait for all spinners to disappear.
 */
public class SpinnersTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SpinnersTest.class);

    @Test
    public void testSpinners() {
        driver.get("file://" + absoluteFilePath("spinners"));
        WebElement spinnerSmall = driver.findElement(By.cssSelector(".spinner-small"));
        WebElement spinnerLarge = driver.findElement(By.cssSelector(".spinner-large"));

        logger.info("{}",
        areSpinnersLoaded(driver, spinnerSmall));
        areSpinnersLoaded(driver, spinnerLarge);
    }

    /**
     * @return true if all spinners have disappeared, false otherwise
     */
    public boolean areSpinnersLoaded(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return !element.isDisplayed();
                } catch (StaleElementReferenceException | NoSuchElementException ex) {
                    return true;
                }
            }
        });
    }
}
