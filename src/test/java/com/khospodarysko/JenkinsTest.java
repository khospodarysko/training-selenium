package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

/**
 * Find only failed tests and print them along with the class they relate to.
 */
public class JenkinsTest extends BaseTest {
    private WebDriverWait waiter;

    @BeforeMethod
    public void openSuite() {
        driver.get("file://" + absoluteFilePath("jenkins-small"));
        // driver.get("file://" + absoluteFilePath("jenkins"));

        waiter = new WebDriverWait(driver, 10);

        waiter.until(elementToBeClickable(By.cssSelector(".button.level0.switch"))).click();
        waiter.until(sizeStable(By.cssSelector(".ztree")));
    }

    private Function<WebElement, String> trimTime = we -> {
        String text = we.getText();
        if (text.indexOf(" ") > 0) {
            return text.substring(0, text.indexOf(" ")).trim();
        }
        return text.trim();
    };

    private Predicate<WebElement> isFailedIgnored = we ->
        !we.findElements(By.cssSelector("span.button[style*='testerr'], span.button[style*='testignored']")).isEmpty();

    private Predicate<WebElement> hasFailedIgnored = we ->
        we.findElements(By.cssSelector("a.level2")).stream().anyMatch(isFailedIgnored);

    @Test
    public void testPrintFailedSkipped() {
        List<WebElement> tests = driver.findElements(By.cssSelector("li.level1"));
        Collections.reverse(tests);
        tests.forEach(item -> item.findElement(By.cssSelector(".switch")).click());

        waiter.until(sizeStable(tests.get(0)));

        Collections.reverse(tests);
        tests.stream()
            .filter(hasFailedIgnored)
            .forEach(item -> {
                System.out.println(trimTime.apply(item.findElement(By.cssSelector("a.level1 .node_name"))));

                item.findElements(By.cssSelector("a.level2")).stream()
                    .filter(isFailedIgnored)
                    .map(trimTime)
                    .forEach(name -> System.out.println("\t" + name));
            });
    }

    // 55 sec
    @Test
    public void testUnfoldingSpeed_Selenium() {
        List<WebElement> tests = driver.findElements(By.cssSelector("li.level1"));
        tests.forEach(item -> {
            item.findElement(By.cssSelector(".switch")).click();
            waiter.until(sizeStable(item));
        });
    }

    // 5 sec
    @Test
    public void testUnfoldingSpeed_SeleniumReverse() {
        List<WebElement> tests = driver.findElements(By.cssSelector("li.level1"));
        Collections.reverse(tests);
        tests.forEach(item -> item.findElement(By.cssSelector(".switch")).click());

        waiter.until(sizeStable(tests.get(0)));
    }

    // 7 sec
    @Test
    public void testUnfoldingSpeed_Actions() {
        Actions actions = new Actions(driver);

        List<WebElement> tests = driver.findElements(By.cssSelector("li.level1"));
        tests.forEach(item -> {
            actions.doubleClick(item.findElement(By.cssSelector("a.level1"))).perform();
        });

        waiter.until(sizeStable(tests.get(tests.size() - 1)));
    }

    // 5 sec
    @Test
    public void testUnfoldingSpeed_JS() {
        List<WebElement> tests = driver.findElements(By.cssSelector("li.level1 .switch"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].forEach(function(item) { item.click(); });", tests);

        waiter.until(sizeStable(By.cssSelector(".ztree")));
    }

    private static ExpectedCondition<Boolean> listStable(List<WebElement> list) {
        return new ExpectedCondition<Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver driver) {
                int before = list.size();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int after = list.size();
                System.out.println(String.format("Before: %d. After: %d", before, after));
                return before != 0 && after != 0 && before == after;
            }
        };
    }

    private static ExpectedCondition<Boolean> sizeStable(By by) {
        return new ExpectedCondition<Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver driver) {
                int before = driver.findElement(by).getSize().getHeight();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int after = driver.findElement(by).getSize().getHeight();
                // System.out.println(String.format("Before: %d. After: %d", before, after));
                return before != 0 && after != 0 && before == after;
            }
        };
    }

    private static ExpectedCondition<Boolean> sizeStable(WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver driver) {
                int before = element.getSize().getHeight();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int after = element.getSize().getHeight();
                // System.out.println(String.format("Before: %d. After: %d", before, after));
                return before != 0 && after != 0 && before == after;
            }
        };
    }
}
