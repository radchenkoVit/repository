package Pages;

import org.openqa.selenium.By;
import utils.WebDriverWrapper;

public class AppManagePage extends BasePage {
    public AppManagePage(WebDriverWrapper driver) {
        super(driver);
    }

    private static By easyUpdateBlock = By.xpath("//div[@class='visual-slider']//img[@alt='Easy Updates']");

    public boolean checkIfEasyUpdateBlockPresent(){
        if (driver.findElements(easyUpdateBlock).size() > 0) {
            driver.makeScreenShot();
            log.info("Easy Update Block Present");
            return true;
        }
        driver.makeScreenShot();
        log.error("Easy Update Block is not Present");
        return false;
    }
}
