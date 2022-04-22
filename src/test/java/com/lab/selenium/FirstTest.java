package com.lab.selenium;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class FirstTest {
    @Test
    public void test() throws IOException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get("https://google.com/ncr");
        webDriver.findElement(By.name("q")).sendKeys(("grape" + Keys.ENTER));
        WebElement imagesButton = wait.until(presenceOfElementLocated(By.xpath("//a[contains(@data-hveid, 'CAIQAw')]")));
        imagesButton.click();
        WebElement imageOfGrape = wait.until(presenceOfElementLocated(By.xpath("//img[contains(@class, 'rg_i Q4LuWd')]")));
        imageOfGrape.click();
        Assert.assertTrue(imageOfGrape.isDisplayed());
        File file = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/java/com/lab/selenium/screen.png"));
        webDriver.quit();
    }
}
