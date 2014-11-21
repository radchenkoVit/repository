package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverWrapper;

public class BasePage {

    protected WebDriverWrapper driver;
    protected WebDriverWait waitForConditions;
    protected Logger log;

    public BasePage(WebDriverWrapper driver) {
        this.driver = driver;
        waitForConditions = new WebDriverWait(driver, 5);
        log = Logger.getLogger(BasePage.class);
    }
}
