package Pages.HomePage;

import Pages.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import utils.WebDriverWrapper;

public class SignUpForm extends BasePage {
    protected Logger log;

    public SignUpForm(WebDriverWrapper driver) {
        super(driver);
        log = Logger.getLogger(BasePage.class);
    }

    private static By signUpWithEmailButton = By.xpath("//a[contains(@class, 'create-using-email')]");

    //Email
    private static By firstNameInput = By.xpath("//div[@id='inputFirst']/input");
    private static By lastNameInput = By.xpath("//div[@id='inputLast']/input");
    private static By emailInput = By.xpath("//div[@id='inputEmail']/input");
    private static By passwordInput = By.xpath("//input[@id='user_password']");
    private static By confirmPasswordInput = By.xpath("//div[@id='inputConfirmPassword']/input");
    private static By airBnbNewsCheckBox = By.xpath("//input[@id='user_profile_info_receive_promotional_email']");
    private static By signUpButton = By.xpath("//form[@id='user_new']//button");

    public void fillDataInSignUpWithEmailForm(String firstName, String lastName, String email, String password, Boolean newsCheckBox){
        driver.inputText(firstName, firstNameInput);
        driver.inputText(lastName, lastNameInput);
        driver.inputText(email, emailInput);
        driver.inputText(password, passwordInput);
        driver.inputText(password, confirmPasswordInput);

        if (newsCheckBox)
            driver.findElement(airBnbNewsCheckBox).click();

        driver.findElement(signUpButton).click();
    }

    public void clickOnSignUpWithEmailButton(){
        driver.findElement(signUpWithEmailButton).click();
    }

    public void inputFirstName(String firstName){
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void inputEmail(String email){
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void inputPassword(String password){
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void inputPassInConfirmPasswordInput(String password){
        driver.findElement(confirmPasswordInput).clear();
        driver.findElement(confirmPasswordInput).sendKeys(password);
    }

    public void selectNewsCheckBox(){
        driver.findElement(airBnbNewsCheckBox).click();
    }

    public void clickOnSignUpButton(){
        driver.findElement(signUpButton).click();
    }

}
