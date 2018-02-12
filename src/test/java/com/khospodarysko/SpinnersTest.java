package com.khospodarysko;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Wait for all spinners to disappear.
 */
public class SpinnersTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SpinnersTest.class);

    @Test
    public void testSpinnersDirectFind() {
        driver.get("file://" + absoluteFilePath("spinners"));

        List<WebElement> spinnerSmall = driver.findElements(By.cssSelector(".spinner-small"));
        List<WebElement> spinnerLarge = driver.findElements(By.cssSelector(".spinner-large"));

        logger.info("{}",
            SpinnerPage.waitUntilAllSpinnersHaveDisappeared(driver, spinnerSmall, spinnerLarge));

        Assertions.assertThat(spinnerSmall)
            .as("Small spinners are not disappeared")
            .isEmpty();

        Assertions.assertThat(spinnerLarge)
            .as("Large spinners are not disappeared")
            .isEmpty();
    }

    @Test
    public void testSpinnersPageObject() {
        driver.get("file://" + absoluteFilePath("spinners"));

        SpinnerPage page = new SpinnerPage(driver);

        logger.info("{}",
            SpinnerPage.waitUntilAllSpinnersHaveDisappeared(driver, page.smallSpinners, page.largeSpinners));

        Assertions.assertThat(page.smallSpinners)
            .as("Small spinners are not disappeared")
            .isEmpty();

        Assertions.assertThat(page.largeSpinners)
            .as("Large spinners are not disappeared")
            .isEmpty();
    }
}

class SpinnerPage {
    private static final Logger logger = LoggerFactory.getLogger(SpinnerPage.class);

    private WebDriver driver;

    public SpinnerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".spinner-small")
    public List<WebElement> smallSpinners;

    @FindBy(css = ".spinner-large")
    public List<WebElement> largeSpinners;

    public static boolean waitUntilAllSpinnersHaveDisappeared(WebDriver driver, List<WebElement> smallSpinner, List<WebElement> largeSpinner) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.ignoring(StaleElementReferenceException.class);
        return wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                logger.info("small {}; large {}", smallSpinner.size(), largeSpinner.size());
                return smallSpinner.isEmpty() && largeSpinner.isEmpty();
            }
        });
    }
}

