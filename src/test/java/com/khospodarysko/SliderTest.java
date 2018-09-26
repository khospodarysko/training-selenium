package com.khospodarysko;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 */
public class SliderTest extends BaseTest {
    @Test
    public void testExpectedCondition() {
        driver.get("file://" + absoluteFilePath("2. slider"));

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        SliderPage page = initPage(driver, SliderPage.class);

        Actions actions = new Actions(driver);
        actions.clickAndHold(page.slider).perform();

        while (!page.value.getText().equals("20")) {
            actions.moveByOffset(-1, 0).build().perform();
        }
        actions.release().build().perform();

        Assertions.assertThat(page.value.getText()).isEqualTo("20");
    }
}

class SliderPage {
    @FindBy(id = "ranger")
    WebElement slider;

    @FindBy(id = "demo")
    WebElement value;
}
