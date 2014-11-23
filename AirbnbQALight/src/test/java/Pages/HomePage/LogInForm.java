package Pages.HomePage;

import Pages.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import utils.WebDriverWrapper;

public class LogInForm extends BasePage {
    protected Logger log;

    public LogInForm(WebDriverWrapper driver) {
        super(driver);
        log = Logger.getLogger(BasePage.class);
    }

    private static By emailInput = By.id("signin_email");
    private static By passwordInput = By.id("signin_password");
    private static By rememberMeCheckBox = By.xpath("//input[@id = 'remember_me2']");
    private static By logInButtonLogInFrom = By.id("user-login-btn");

    private static By logInViaFaceBookButton = By.xpath("//a[contains(@class, 'btn-facebook')]");
    private static By logInWithGoogleButton = By.xpath("//a[contains(@class, 'btn-google')]");

    public void inputEmail(String email){
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        log.info("Entered email - " + email);
    }

    public void inputPassword(String password){
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
        log.info("Entered email - " + password);
    }

    public void selectRememberMeCheckBox(){
        driver.findElement(rememberMeCheckBox).click();
        log.info("Selected Check Box");
    }

    public void unSelectRememberMeCheckBox(){
        if (driver.findElement(rememberMeCheckBox).isSelected()) {
            driver.findElement(rememberMeCheckBox).click();
            log.info("UnSelected Check Box");
        }
    }

    public void clickOnLogInButton(){
        driver.findElement(logInButtonLogInFrom).click();
        log.info("LogIn button was clicked");
    }

    public void clickOnFaceBookLogInButton(){
        driver.findElement(logInViaFaceBookButton).click();
        log.info("LogIn button via FaceBook was clicked");
    }

    public void clickOnMailLogInButton(){
        driver.findElement(logInWithGoogleButton).click();
    }

}
