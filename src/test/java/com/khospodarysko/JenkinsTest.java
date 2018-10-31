package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class JenkinsTest extends BaseTest {
    private WebDriverWait waiter;

    @BeforeMethod
    public void openSuite() {
        driver.get("https://khospodarysko.github.io/training-selenium/9.2.%20jenkins.html");

        waiter = new WebDriverWait(driver, 10);

        waiter.until(elementToBeClickable(By.cssSelector(".button.level0.switch"))).click();
        waiter.until(sizeStable(By.cssSelector(".ztree")));
    }

    @Test
    public void testPrintFailedSkipped_OOP() {
        // List<WebElement> tests = driver.findElements(By.cssSelector("li.level1 .switch"));
        // ((JavascriptExecutor) driver).executeScript("arguments[0].forEach(function(item) { item.click(); });", tests);
        // waiter.until(sizeStable(By.cssSelector(".ztree")));
        testUnfoldingSpeed_JS();

        JenkinsPage page = new JenkinsPage();
        PageFactory.initElements(driver, page);

        // for (ClassWrapper cw : page.classWrappers()) {
        for (ClassWrapper cw : page.failedIgnoreClassWrappers()) {
            if (cw.hasFailed() || cw.hasIgnored()) {
                System.out.println(cw.getName());
                for (MethodWrapper mw : cw.methodWrappers()) {
                    if (mw.isFailed()) {
                        System.out.println("\t failed  - " + mw.getName());
                    }
                    if (mw.isIgnored()) {
                        System.out.println("\t ignored - " + mw.getName());
                    }
                }
            }
        }
    }

    @Test
    public void testPrintFailedSkipped_Functional() {
        List<WebElement> tests = driver.findElements(By.cssSelector("li.level1"));
        Collections.reverse(tests);
        tests.forEach(item -> item.findElement(By.cssSelector(".switch")).click());

        waiter.until(sizeStable(By.cssSelector(".ztree")));

        Collections.reverse(tests);
        tests.stream()
            .filter(hasFailedIgnored)
            .forEach(item -> {
                System.out.println(trimTime.apply(item.findElement(By.cssSelector("a.level1 .node_name"))));

                List<WebElement> methods = item.findElements(By.cssSelector("a.level2"));
                for (WebElement method : methods) {
                    String methodName = trimTime.apply(method);
                    if (isFailed.test(method)) {
                        System.out.println("\t failed  - " + methodName);
                    }
                    if (isIgnored.test(method)) {
                        System.out.println("\t ignored - " + methodName);
                    }
                }
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
            @Override
            public Boolean apply(WebDriver driver) {
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
            @Override
            public Boolean apply(WebDriver driver) {
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
            @Override
            public Boolean apply(WebDriver driver) {
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

    private Function<WebElement, String> trimTime = we -> {
        String text = we.getText();
        if (text.indexOf(" ") > 0) {
            return text.substring(0, text.indexOf(" ")).trim();
        }
        return text.trim();
    };

    private Predicate<WebElement> isFailedIgnored = we ->
        !we.findElements(By.cssSelector("span.button[style*='testerr'], span.button[style*='testignored']")).isEmpty();

    private Predicate<WebElement> isFailed = we ->
        !we.findElements(By.cssSelector("span.button[style*='testerr']")).isEmpty();

    private Predicate<WebElement> isIgnored = we ->
        !we.findElements(By.cssSelector("span.button[style*='testignored']")).isEmpty();

    private Predicate<WebElement> hasFailedIgnored = we ->
        we.findElements(By.cssSelector("a.level2")).stream().anyMatch(isFailedIgnored);
}

class JenkinsPage {
    @FindBy(css = "li.level1")
    private List<WebElement> classes;

    @FindBy(xpath = "//li[@class='level1'][.//*[contains(@style, 'testerr')] or .//*[contains(@style, 'testignored')]]")
    private List<WebElement> failedIgnoredClasses;

    public List<ClassWrapper> classWrappers() {
        return classes.stream().map(we -> new ClassWrapper(we)).collect(Collectors.toList());
    }

    public List<ClassWrapper> failedIgnoreClassWrappers() {
        return failedIgnoredClasses.stream().map(we -> new ClassWrapper(we)).collect(Collectors.toList());
    }

    public static Function<WebElement, String> trimTime = we -> {
        String text = we.getText();
        if (text.indexOf(" ") > 0) {
            return text.substring(0, text.indexOf(" ")).trim();
        }
        return text.trim();
    };
}

class ClassWrapper {
    private WebElement wrapped;

    public ClassWrapper(WebElement wrapped) {
        this.wrapped = wrapped;
    }

    private List<WebElement> getMethods() {
        return wrapped.findElements(By.cssSelector("li.level2"));
    }

    public List<MethodWrapper> methodWrappers() {
        return getMethods().stream().map(we -> new MethodWrapper(we)).collect(Collectors.toList());
    }

    public boolean hasFailed() {
        return methodWrappers().stream().anyMatch(mw -> mw.isFailed());
    }

    public boolean hasIgnored() {
        return methodWrappers().stream().anyMatch(mw -> mw.isIgnored());
    }

    public String getName() {
        return JenkinsPage.trimTime.apply(wrapped.findElement(By.cssSelector("a.level1 .node_name")));
    }
}

class MethodWrapper {
    private WebElement wrapped;

    public MethodWrapper(WebElement wrapped) {
        this.wrapped = wrapped;
    }

    public boolean isFailed() {
        return !wrapped.findElements(By.cssSelector("span.button[style*='testerr']")).isEmpty();
    }

    public boolean isIgnored() {
        return !wrapped.findElements(By.cssSelector("span.button[style*='testignored']")).isEmpty();
    }

    public String getName() {
        return JenkinsPage.trimTime.apply(wrapped.findElement(By.cssSelector("a.level2 .node_name")));
    }
}
