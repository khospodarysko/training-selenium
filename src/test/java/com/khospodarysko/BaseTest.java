package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;

public class BaseTest {
    public WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-mac");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String absoluteFilePath(String name) {
        File html = new File("src/main/resources/"+name+".html");
        return html.getAbsolutePath();
    }

    protected static <T> T initPage(WebDriver driver, Class<T> page) {
        try {
            T t = page.newInstance();
            PageFactory.initElements(driver, t);
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Could not init page " + page.getCanonicalName());
        }
    }
}
