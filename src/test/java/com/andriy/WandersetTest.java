package com.andriy;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class WandersetTest {
    private WebDriver driver;
    private Waits waits;
    private JSUtils jsUtils;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-mac");
        driver = new ChromeDriver();
        waits = new Waits(driver);
        jsUtils = new JSUtils(driver);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void testShopIsMenu() {
        driver.get("https://wanderset.com/");
        MenuComponent menuComponent = new MenuComponent(driver);

        waits.forElement(menuComponent.shopMenu);

        // 1 way - just checks attributes that are specific to link
        Assert.assertEquals(menuComponent.shopMenu.getTagName(), "a", "Element menu 'shop' was not a link");
        Assert.assertFalse(menuComponent.shopMenu.getAttribute("href").isEmpty(), "Element menu 'shop' did not have 'href' attribute");

        // 2 way - click and see if page is opened
        menuComponent.shopMenu.click();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/brand/new-arrivals"), "Click on 'shop' menu did not open new page");
    }

    @Test
    public void testFootwearIsMenu() {
        driver.get("https://wanderset.com/");
        MenuComponent menuComponent = new MenuComponent(driver);

        Actions hoverShop = new Actions(driver);
        hoverShop.moveToElement(menuComponent.shopMenu).perform();

        waits.forElement(menuComponent.footwearSubMenu);

        List<String> menuItemTexts = menuComponent.footwearSubMenuItems.stream()
            .map(element -> element.getText())
            .collect(Collectors.toList());

        String mainPageUrl = driver.getCurrentUrl();
        for (String itemText : menuItemTexts) {
            WebElement menu = menuComponent.findMenuByText(itemText);
            String menuLink = menu.getAttribute("href");
            String menuText = menu.getText();
            // menu.findElement(By.xpath("./..")).click(); // FIXME does not work as element is not visible
            // jsUtils.click(menu);
            WebElement mmm = menu.findElement(By.xpath("./.."));
            jsUtils.click(mmm);

            Assert.assertTrue(driver.getCurrentUrl().endsWith(menuLink), "Click on '" + menuText + "' submenu did not open new page");
            driver.get(mainPageUrl);
        }
    }

    @Test
    public void testFootwearMenuHas6Items() {
        driver.get("https://wanderset.com/");
        MenuComponent menuComponent = new MenuComponent(driver);

        Actions hoverShop = new Actions(driver);
        hoverShop.moveToElement(menuComponent.shopMenu).perform();

        waits.forElement(menuComponent.footwearSubMenu);

        Assert.assertEquals(menuComponent.footwearSubMenuItems.size(), 6, "'Footwear' submenu should have 6 items");
    }
}

class MenuComponent {
    private WebDriver driver;

    @FindBy(xpath = "//a[text()='shop']")
    public WebElement shopMenu;

    @FindBy(xpath = "//a[text()='All footwear']")
    public WebElement footwearSubMenu;

    // @FindBy(xpath = "//ul[.//a[text()='All footwear']]//li[@class='list-dropdown-menu__item']")
    @FindBy(xpath = "//ul[.//a[text()='All footwear']]//a[contains(@href, '/products/category/footwear/subcategory')]")
    public List<WebElement> footwearSubMenuItems;

    public MenuComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement findMenuByText(String text) {
        return driver.findElement(By.xpath("//a[contains(@href, '/products/')][text()='" + text + "']"));
    }
}

class Waits {
    private static final int TIMEOUT = 10;

    private WebDriverWait wait;

    public Waits(WebDriver driver) {
        wait = new WebDriverWait(driver, TIMEOUT);
    }

    public void forElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}

class JSUtils {
    private WebDriver driver;

    public JSUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
