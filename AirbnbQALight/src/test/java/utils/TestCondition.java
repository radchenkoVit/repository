package utils;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class TestCondition {

    protected static WebDriverWrapper driver;
    protected Logger log;

    @BeforeSuite
    public void setUp() throws Exception {
        log = Logger.getLogger(TestCondition.class);
        driver = new WebDriverFactory().initDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

}
