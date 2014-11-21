package Pages;

import org.openqa.selenium.By;
import utils.WebDriverWrapper;

public class HomePage extends BasePage {
    public HomePage(WebDriverWrapper driver) {
        super(driver);
    }

    public static By signUpButton = By.xpath("//a[contains(@class, 'sign-up-now')]");
    public static By featureButton = By.xpath("//nav/a[@href='/features/frameworks']");


    public void open(){
        driver.manage().deleteAllCookies();
        driver.get("http://devmate.com/");
        log.info("Home page is opened");
    }

    public SignUpPage clickOnSignUpNowButton(){
        driver.findElement(signUpButton).click();
        log.info("SignUp Button was clicked");
        driver.waitAndAssertURL("http://devmate.com/signup");
        return new SignUpPage(driver);
    }

    public FrameWorksPage clickOnFeatureButton(){
        driver.findElement(featureButton).click();
        log.info("Feature Button was clicked");
        driver.waitAndAssertURL("http://devmate.com/features/frameworks");
        return new FrameWorksPage(driver);
    }

}
