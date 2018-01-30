package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseWebElementList extends BaseTest {
    @Test
    public void testReverseDoesNotWork() {
        driver.get("https://docs.oracle.com/javase/8/docs/api/java/util/List.html");

        Page page = new Page();
        PageFactory.initElements(driver, page);

        System.out.println(page.methods.stream().map(WebElement::getText).collect(Collectors.toList()));
        Collections.reverse(page.methods);
        System.out.println(page.methods.stream().map(WebElement::getText).collect(Collectors.toList()));
    }

    @Test
    public void testCopyList() {
        driver.get("https://docs.oracle.com/javase/8/docs/api/java/util/List.html");

        Page page = new Page();
        PageFactory.initElements(driver, page);

        System.out.println(page.methods.stream().map(WebElement::getText).collect(Collectors.toList()));
        ArrayList<WebElement> copy = new ArrayList<>(page.methods);
        Collections.reverse(copy);
        System.out.println(copy.stream().map(WebElement::getText).collect(Collectors.toList()));
    }

    @Test
    public void testWhy() {
        driver.get("https://docs.oracle.com/javase/8/docs/api/java/util/List.html");

        EventFiringWebDriver firingWebDriver = new EventFiringWebDriver(driver);
        firingWebDriver.register(new AbstractWebDriverEventListener() {
            @Override
            public void beforeFindBy(By by, WebElement element, WebDriver driver) {
                System.out.println("before find: " + by);
            }
        });
        this.driver = firingWebDriver;

        Page page = new Page();
        PageFactory.initElements(driver, page);

        List<WebElement> localList = (List) Proxy.newProxyInstance
            (page.methods.getClass().getClassLoader(),
                new Class[]{ List.class },
                (proxy, method, args) -> {
                    System.out.println("proxy " + method.getName());
                    return method.invoke(page.methods, args);
                });

        System.out.println(localList.stream().map(WebElement::getText).collect(Collectors.toList()));
        Collections.reverse(localList);
        System.out.println(localList.stream().map(WebElement::getText).collect(Collectors.toList()));
    }
}

class Page {
    @FindBy(css = "span.memberNameLink")
    public List<WebElement> methods;
}