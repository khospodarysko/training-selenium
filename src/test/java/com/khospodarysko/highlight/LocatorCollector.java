package com.khospodarysko.highlight;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LocatorCollector extends AbstractWebDriverEventListener {
    public static Map<String, List<By>> testMethodBys = new HashMap<>();

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        String testFullName = MDC.get("testFullName");
        List<By> byList = testMethodBys.get(testFullName);
        if (byList == null) {
            byList = new ArrayList<>();
        }
        byList.add(by);
        testMethodBys.put(testFullName, byList);
    }
}
