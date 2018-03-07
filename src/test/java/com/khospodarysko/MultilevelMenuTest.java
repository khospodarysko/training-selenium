package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Click on "Time" menu and check if printed time was the same as current system time.
 * UI, JS Executor
 */
public class MultilevelMenuTest extends BaseTest {
    @Test
    public void test() {
        driver.get("file://" + absoluteFilePath("multilevel-menu"));

        MultilevelPage page = new MultilevelPage(driver);

        page.waitFor(page.menuBy);

        page.clickMenu();
        page.clickLanguages();
        page.clickTime();

        System.out.println(page.getTime());
    }
}

class MultilevelPage {
    private WebDriver driver;
    private WebDriverWait driverWait;

    public By menuBy = By.id("css-menu");

    public MultilevelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driverWait = new WebDriverWait(driver, 10);
    }

    public void waitFor(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.pollingEvery(100, TimeUnit.MILLISECONDS);
            wait.until(driver -> {
                System.out.println(driver.findElement(by).isDisplayed());
                return false;
            });
        } catch (Exception ex) {
            // ignore
        }
    }

    public void clickMenu() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(menuBy)).click();
    }

    public void clickLanguages() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[for='link-gallery']"))).click();
    }

    public void clickTime() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Time']"))).click();
    }

    public String getTime() {
        return driverWait.until(driver -> {
            String text = driver.findElement(By.id("time")).getText();
            return text.isEmpty() ? null : text;
        });
    }
}
