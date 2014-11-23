package Test;

import Pages.HomePage.HomePage;
import Pages.UserHomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.TestCondition;

import static utils.DataGenerator.*;

public class SignUpTest extends TestCondition {

    @DataProvider(name = "UserRegistration")
    public Object[][] provideData() {
        return new Object[][]{
                {randomFirstName(), randomLastName(), randomEmail(), randomPassword(), false},
                {randomFirstName(), randomLastName(), randomEmail(), randomPassword(), true},
        };
    }

    @Test(dataProvider = "UserRegistration")
    public void signUpViaEmail(String firstName, String lastName, String email, String password, Boolean newsCheckBox){
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickOnSignUpButton();
        UserHomePage userHomePage = homePage.fillSignUpDataWithEmailAndSubmit(firstName, lastName, email, password, newsCheckBox);
        Assert.assertTrue(userHomePage.checkIfUserLoggedIn());
    }

}
