package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    /* Browsers constants */
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";

    public WebDriverWrapper initDriver (String browser)
    {
        WebDriverWrapper testDriver = setDefaultConfiguration(browser);
        return testDriver;
    }

    private WebDriverWrapper setDefaultConfiguration(String browser)
    {
        WebDriver driver = null;

        if (browser.equals(FIREFOX))
        {
            driver = new FirefoxDriver();
        } else
        if (browser.equals(CHROME))
        {
            driver = new ChromeDriver();
        }

        WebDriverWrapper testDriver = new WebDriverWrapper(driver);
        return testDriver;
    }

}
