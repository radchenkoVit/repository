package Pages.HomePage;

import Pages.BasePage;
import Pages.UserHomePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WebDriverWrapper;

public class HomePage extends BasePage {
    protected Logger log;
    LogInForm logInForm;
    SignUpForm signUpForm;

    public HomePage(WebDriverWrapper driver) {
        super(driver);
        log = Logger.getLogger(BasePage.class);
        logInForm = new LogInForm(driver);
        signUpForm = new SignUpForm(driver);
    }

    private static By logInButton = By.xpath(".//li[@id='login']/a");
    private static By signUpButton = By.xpath("//li[@id='sign_up']/a");

    private static By logInFormElement = By.xpath("//div[@class='signup modal-content']//a[@href='/signup_login']");
    private static By signUpFormElement = By.xpath("//div[@class='signup modal-content']//a[@href='/login']");


    public void open(){
        driver.manage().deleteAllCookies();
        driver.get("https://www.airbnb.com/");
        log.info("Home Page was opened");
    }

    public void clickOnLoginButton(){
        waitForConditions.until(ExpectedConditions.elementToBeClickable(logInButton));
        driver.findElement(logInButton).click();
        log.info("LogIn button was clicked");
        waitForConditions.until(ExpectedConditions.visibilityOfElementLocated(logInFormElement));
    }

    public void clickOnSignUpButton(){
        waitForConditions.until(ExpectedConditions.visibilityOfElementLocated(signUpButton));
        driver.findElement(signUpButton).click();
        log.info("SignUp button was clicked");
        waitForConditions.until(ExpectedConditions.visibilityOfElementLocated(signUpFormElement));
    }

    public UserHomePage fillLogInDataAndLogIn(String email, String password, Boolean selectCheckBox){
        logInForm.inputEmail(email);
        logInForm.inputPassword(password);

        if (selectCheckBox)
            logInForm.selectRememberMeCheckBox();

        logInForm.clickOnLogInButton();
        return new UserHomePage(driver);
    }

    public void fillLogInDataAndClickLogInButton(String email, String password, Boolean selectCheckBox) {
        logInForm.inputEmail(email);
        logInForm.inputPassword(password);

        if (selectCheckBox)
            logInForm.selectRememberMeCheckBox();
    }

    public UserHomePage logInViaFaceBook(String email, String password){
        By emailInput = By.id("email");
        By passwordInput = By.id("pass");
        By loginButton = By.id("loginbutton");

        driver.setParentWindow();
        logInForm.clickOnFaceBookLogInButton();

        for (String windowHandler : driver.getWindowHandles())
            driver.switchTo().window(windowHandler);

        waitForConditions.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);

        driver.findElement(loginButton).click();

        for (String windowHandler : driver.getWindowHandles())
            driver.switchTo().window(windowHandler);

        return new UserHomePage(driver);
    }

    public UserHomePage fillSignUpDataWithEmailAndSubmit(String firstName, String lastName, String email, String password, Boolean newsCheckBox){
        signUpForm.clickOnSignUpWithEmailButton();
        signUpForm.inputFirstName(firstName);
        signUpForm.inputLastName(lastName);
        signUpForm.inputEmail(email);
        signUpForm.inputPassword(password);
        signUpForm.inputPassInConfirmPasswordInput(password);

        if (!newsCheckBox)
            signUpForm.selectNewsCheckBox();

        signUpForm.clickOnSignUpButton();

        return new UserHomePage(driver);
    }


}
