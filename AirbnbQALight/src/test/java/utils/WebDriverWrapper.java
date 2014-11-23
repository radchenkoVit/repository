package utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
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
    private static String parentWindow;
    private static String childWindow;

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

    public void takeScreenShot(){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(".\\screenShots\\screenshot_" + WebDriverWrapper.screenNumber + ".png"));
            WebDriverWrapper.screenNumber++;
        } catch (IOException e) {
            log.error("Can not take a screenshot");
            e.printStackTrace();
        }
    }

    public void takeScreenShot(String screenShotName){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(".\\screenShots\\" + screenShotName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Can not take a screenshot");
        }
    }

    //Test func //new func
    public void inputText(String text, By locator) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
        log.info("Entered " + text);
    }

    public void selectCheckBox(By checkBoxLocator){
        if (!driver.findElement(checkBoxLocator).getAttribute("checked").equals("checked")) {
            driver.findElement(checkBoxLocator).click();
            log.info("Selected " + checkBoxLocator);
        }
    }

    public void unSelectCheckBox(By checkBoxLocator){
        if (driver.findElement(checkBoxLocator).getAttribute("checked").equals("checked")) {
            driver.findElement(checkBoxLocator).click();
            log.info("UnSelected " + checkBoxLocator);
        }
    }

    public void selectFromListOnclick(By listLocator, String listValue) {
        driver.findElement(listLocator).click();
        new Select(driver.findElement(listLocator)).selectByVisibleText(listValue);
    }

    public void selectFromListOnFocus(By listLocator, String listValue) {
        WebElement list = driver.findElement(listLocator);
        new Actions(driver).moveToElement(list).perform();
        //new Actions(driver).moveToElement(list).click(list.findElement(By.xpath("//*[contains(text(), '"+listValue+"')]"))).perform();
        new Select(list).selectByVisibleText(listValue);
    }

    public void selectFromListOnFocus(By listLocator, By listValue) {
        WebElement list = driver.findElement(listLocator);
        new Actions(driver).moveToElement(list).moveToElement(list.findElement(listValue)).click().perform();
    }

    public void focusOnElement(By elementLocator) throws Exception {
        new Actions(driver).moveToElement(driver.findElement(elementLocator)).perform();
    }

    public void clickOnElementAndSwitchToWindow(By element) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        parentWindow = driver.getWindowHandle();
        driver.findElement(element).click();

        for (String windowHandler : driver.getWindowHandles())
            driver.switchTo().window(windowHandler);

        childWindow = driver.getWindowHandle();
    }

    public void setParentWindow() {
        WebDriverWrapper.parentWindow = driver.getWindowHandle() ;
    }

    public void goToParentWindow(){
        if (childWindow != null && !childWindow.equals("") && driver.getWindowHandle().equals(childWindow) && !driver.getWindowHandle().equals(parentWindow)) {
            driver.close();
            driver.switchTo().window(parentWindow);
        }
    }

    public void waitForAjaxResponse(int timeoutSeconds) throws InterruptedException {
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

        for (int i = 0; i < timeoutSeconds; i++) {
            Long numberOfConnections = (Long) jsDriver.executeScript("return jQuery.active");
            log.debug("Number of active jQuery Ajax calls is <" + numberOfConnections + ">");

            if (numberOfConnections == 0)
                break;
            Thread.sleep(500);
        }
    }

    //need to test
    public void waitForAjaxResponse() throws InterruptedException {
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        Long numberOfConnections = (Long) jsDriver.executeScript("return jQuery.active");

        for (int i = 0; i < numberOfConnections; i++) {
            numberOfConnections = (Long) jsDriver.executeScript("return jQuery.active");
            log.debug("Number of active jQuery Ajax calls is <" + numberOfConnections + ">");

            if (numberOfConnections == 0)
                break;
            Thread.sleep(500);
        }
    }

}
