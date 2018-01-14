package com.khospodarysko.ztree;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khospodarysko.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZTreeTest extends BaseTest {
    @Test
    public void test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ZTree tree = new ZTree();

        List<ZTree.Node> nodes = new ArrayList<>();

        ZTree.Node node1 = new ZTree.Node();
        node1.name = "node 1";

        ZTree.Node node2 = new ZTree.Node();
        node2.name = "node 2";

        List<ZTree.Node.Child> children1 = new ArrayList<>();
        List<ZTree.Node.Child> children2 = new ArrayList<>();

        ZTree.Node.Child child1 = new ZTree.Node.Child();
        child1.name = "child 1";

        ZTree.Node.Child child2 = new ZTree.Node.Child();
        child2.name = "child 2";

        children1.add(child1);
        children2.add(child2);
        nodes.add(node1);
        nodes.add(node2);

        node1.children = children1;
        node2.children = children2;
        tree.nodes = nodes;

        objectMapper.writeValue(new File("jenkins.json"), tree);
    }

    @Test
    public void readJenkinsTestInProgress() throws IOException, InterruptedException {
        // driver.get("https://jenkins-big.clearslideng.com/job/Regression-Sutro/459/testinprogress/");
        // driver.get("https://jenkins-big.clearslideng.com/job/Regression-GrowthHackers/443/testinprogress/");
        // driver.get("https://jenkins-big.clearslideng.com/job/Regression-Kevlar/457/testinprogress/");
        // driver.get("https://jenkins-big.clearslideng.com/job/Regression-Mutants/457/testinprogress/");
        driver.get("https://jenkins-big.clearslideng.com/job/Regression-Rolodex/459/testinprogress/");

        driver.findElement(By.name("j_username")).sendKeys("khospodarysko");
        driver.findElement(By.name("j_password")).sendKeys("Tya848@k1234");
        driver.findElement(By.id("yui-gen1-button")).click();

        WebDriverWait resultsWait = new WebDriverWait(driver, 60);
        resultsWait.until(driver1 -> driver1.findElement(By.cssSelector("li.level1 a.level1")).isDisplayed());

        Thread.sleep(15000);

        ((JavascriptExecutor) driver).executeScript(
            "$$(\"span.button.level1.switch\").forEach(function(item) {\n" +
                "    item.click();\n" +
                "});");

        Thread.sleep(5000);

        ZTree tree = new ZTree();
        List<ZTree.Node> nodes = new ArrayList<>();

        List<WebElement> testClasses = driver.findElements(By.cssSelector("li.level1"));
        for (WebElement test : testClasses) {
            ZTree.Node node = new ZTree.Node();
            node.name = test.findElement(By.cssSelector("a.level1")).getText();

            System.out.println(node.name);

            List<ZTree.Node.Child> children = new ArrayList<>();

            List<WebElement> testMethods = test.findElements(By.cssSelector("ul.level1 a.level2"));
            for (WebElement method : testMethods) {
                ZTree.Node.Child child = new ZTree.Node.Child();
                child.name = method.getText();

                System.out.println("    " + child.name);

                if (!method.findElements(By.cssSelector(".testError_ico_docu")).isEmpty()) {
                    child.icon = "jenkins/testerr.gif";
                }
                if (!method.findElements(By.cssSelector(".testIgnored_ico_docu")).isEmpty()) {
                    child.icon = "jenkins/testignored.gif";
                }

                children.add(child);
            }

            node.children = children;

            nodes.add(node);
        }

        tree.nodes = nodes;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("jenkins.json"), nodes);
    }
}
