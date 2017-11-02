package com.imdb.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseUITestCase {

    protected static WebDriver driver = null;


    public void beforeMethod(){
        System.setProperty("webdriver.gecko.driver","./src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    public void afterMethod(){
        driver.quit();
    }

}
