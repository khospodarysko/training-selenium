package com.khospodarysko;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Wait for all spinners to disappear.
 */
public class SpinnersTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SpinnersTest.class);

    @Test
    public void testSpinners() {
        driver.get("file://" + absoluteFilePath("spinners"));
        List<WebElement> spinnerSmall = driver.findElements(By.cssSelector(".spinner-small"));
        List<WebElement> spinnerLarge = driver.findElements(By.cssSelector(".spinner-large"));

        logger.info("{}",
        SpinnerPage.waitUntilAllSpinnersHaveDisappeared(driver, spinnerSmall, spinnerLarge));

        Assert.assertTrue(SpinnerPage.isElementNotDisplayed(spinnerSmall), "Small spinners are not disappeared");
        Assert.assertTrue(SpinnerPage.isElementNotDisplayed(spinnerLarge), "Large spinners are not disappeared");
    }

    /**
     * @return true if all spinners have disappeared, false otherwise
     */
}

class SpinnerPage {
    protected WebDriver driver;

    public SpinnerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".spinner-small")
    WebElement smallSpinner;

    @FindBy(css = ".spinner-large")
    WebElement largeSpinner;

    public static boolean waitUntilAllSpinnersHaveDisappeared(WebDriver driver, List<WebElement> smallSpinner, List<WebElement> largeSpinner) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return (smallSpinner.isEmpty()) && (largeSpinner.isEmpty());
                } catch (StaleElementReferenceException | NoSuchElementException ex) {
                    return true;
                }
            }
        });
    }

    public static boolean isElementNotDisplayed(List<WebElement> element) {
        return element.isEmpty();
    }
}

