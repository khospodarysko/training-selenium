package com.khospodarysko;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TimyMCETest extends BaseTest {
    /**
     * Enter bold text into editor, given that there is already text present.
     * First it is needed to clear that, click B, and then enter text.
     */
    @Test
    public void testEnterBoldText() throws InterruptedException {
        driver.get("file://" + absoluteFilePath("tinymce/index"));

        driver.switchTo().frame("mce_0_ifr");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("#tinymce p")).click();
        Thread.sleep(1000);

        Action delete = new Actions(driver).sendKeys(Keys.BACK_SPACE).build();
        for (int i = 0; i < 20; i++) {
            delete.perform();
        }
        Thread.sleep(1000);

        driver.switchTo().defaultContent();
        driver.findElement(By.id("mceu_3-button")).click();
        Thread.sleep(1000);

        driver.switchTo().frame("mce_0_ifr");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("#tinymce p")).click();
        Thread.sleep(1000);

        new Actions(driver).sendKeys("kostya").build().perform();

        System.out.println(1);
    }
}
