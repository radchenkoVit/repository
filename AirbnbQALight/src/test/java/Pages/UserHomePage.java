package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import utils.WebDriverWrapper;

public class UserHomePage extends BasePage {
    protected Logger log;
    public UserHomePage(WebDriverWrapper driver) {
        super(driver);
        log = Logger.getLogger(BasePage.class);
    }

    public boolean checkIfUserLoggedIn(){
        if (driver.findElements(By.xpath(".//a[@id='header-avatar-trigger']")).size() > 0 ){
            log.info("User is logged in");
            return true;
        }

        log.error("User is not logged in");
        return false;
    }

    public void logOut(){
        driver.selectFromListOnFocus(By.xpath("//a[@id='header-avatar-trigger']"), By.xpath("//a[@id='header-logout']"));
    }
}
