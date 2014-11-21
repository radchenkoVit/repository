package utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class WebDriverWrapper implements WebDriver {
    private WebDriver driver;
    private static Wait<WebDriver> wait;
    private Logger log;
    private static int screenNumber = 1;

    private static final long MAX_TIMEOUT = 15;

    // ****************** Base wait before act methods ********************

    public WebDriverWrapper(WebDriver driver)
    {
        this.driver = driver;
        log = Logger.getLogger(WebDriverWrapper.class);
    }

    public boolean waitAndVerifyElementPresent(String name, final By by) {

        wait = new WebDriverWait(driver, MAX_TIMEOUT);
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver dr) {
                    return dr.findElement(by) != null;
                }
            });
        } catch (Exception e) {
            log.error("Element" + name + " is not present");
            return false;
        }
        log.info("Element" + name + " is present");
        return true;
    }

    public boolean waitAndAssertElementPresent(String name, final By by) {

        wait = new WebDriverWait(driver, MAX_TIMEOUT);
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver dr) {
                    return dr.findElement(by) != null;
                }
            });
        } catch (Exception e) {
            Assert.fail("Element" + name + " is not present");
        }
        log.info("Element" + name + " is present");
        return true;
    }

    public boolean waitAndAssertURL(final String url) {
        try {
            wait = new WebDriverWait(driver, MAX_TIMEOUT);
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver dr) {
                    return driver.getCurrentUrl().contains(url);
                }
            });
        } catch (Exception e) {
            Assert.fail("Page" + url + " is not loaded");
        }
        return true;
    }

    public void waitPageToLoad() throws InterruptedException {
        wait = new WebDriverWait(driver, MAX_TIMEOUT);
        final JavascriptExecutor js = (JavascriptExecutor) driver;

        while (!(boolean)js.executeScript("return document.readyState").equals("complete")){
            Thread.sleep(100);
        }

    }

    public void get(String url) {
        try {
            driver.get(url);
        } catch (UnhandledAlertException e) {
        }
    }

    public String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (UnhandledAlertException e) {
        }
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
        try{
            return driver.findElement(by);
        }
        catch (Exception e)
        {
            Assert.fail("Not find"+ by.toString() + " element");
        }
        return null;
    }

    public String getPageSource() {
        return getPageSource();
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public WebDriver.TargetLocator switchTo() {
        return driver.switchTo();
    }

    public WebDriver.Navigation navigate() {
        return driver.navigate();
    }

    public WebDriver.Options manage() {
        return driver.manage();
    }

    public void makeScreenShot(){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(".\\screenShots\\screenshot_" + WebDriverWrapper.screenNumber + ".png"));
            WebDriverWrapper.screenNumber++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
