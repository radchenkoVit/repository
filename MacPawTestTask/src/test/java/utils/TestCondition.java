package utils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class TestCondition {

    protected static WebDriverWrapper driver;

    @BeforeSuite
    public void setUp() throws Exception {
        driver = new WebDriverFactory().initDriver("firefox");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

}
