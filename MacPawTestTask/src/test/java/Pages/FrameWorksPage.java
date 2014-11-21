package Pages;

import org.openqa.selenium.By;
import utils.WebDriverWrapper;

public class FrameWorksPage extends BasePage {
    public FrameWorksPage(WebDriverWrapper driver) {
        super(driver);
    }

    public static By sparkleFrameBlock = By.xpath("//div[@class='visual-slider-img on']/img[contains(@alt, 'framework')]");
    public static By tabAppManagementButton = By.xpath("//div[@class='tabs']/a[@href='/features/app-management']");

    public void checkSparkleFrameBlock(){
        driver.waitAndAssertElementPresent("Sparkle Frame Block is present", sparkleFrameBlock);
        driver.makeScreenShot();
        log.info("Sparkle Frame Block is present");
    }

    public AppManagePage clickAppManagementButton(){
        driver.findElement(tabAppManagementButton).click();
        log.info("App Management was clicked");
        driver.waitAndAssertURL("http://devmate.com/features/app-management");
        return new AppManagePage(driver);
    }
}
