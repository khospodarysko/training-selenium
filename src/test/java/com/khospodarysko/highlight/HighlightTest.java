package com.khospodarysko.highlight;

import com.khospodarysko.BaseTest;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.MDC;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Listeners({ HighlightListener.class })
public class HighlightTest extends BaseTest {
    @BeforeMethod
    public void aBeforeMethod(Method method) {
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        LocatorCollector listener = new LocatorCollector();
        eventDriver.register(listener);

        driver = eventDriver;

        MDC.put("testFullName", method.getName());
    }

    @Test
    public void test() {
        driver.get("file://" + absoluteFilePath("drag-below"));

        // case 1
        Assertions.assertThat(driver.findElement(By.xpath("//*[text()='Button 1']")).getText()).isEqualTo("Button 1");   // green
        Assertions.assertThat(driver.findElement(By.xpath("//*[text()='Button 2']")).getText()).isEqualTo("Button 2");   // green
        Assertions.assertThat(driver.findElement(By.xpath("//*[text()='Button 3']")).getText()).isEqualTo("Button 333"); // red

        // case 2
        // Assertions.assertThat(driver.findElement(By.xpath("//*[text()='Button 3']")).getText()).isEqualTo("Button 333"); // red
        // Assertions.assertThat(driver.findElement(By.xpath("//*[text()='Button 1']")).getText()).isEqualTo("Button 1");   // ?
        // Assertions.assertThat(driver.findElement(By.xpath("//*[text()='Button 2']")).getText()).isEqualTo("Button 2");   // ?
    }
}

