package Test;

import Pages.HomePage.HomePage;
import Pages.UserHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestCondition;
import org.testng.annotations.*;

public class LogInTest extends TestCondition {

    @Test
    public void checkLogInTest(){
        log.info("Log in test via email is started");
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickOnLoginButton();
        UserHomePage userHomePage = homePage.fillLogInDataAndLogIn("vit.radchenko@gmail.com", "QWErty654321", true);
        Assert.assertTrue(userHomePage.checkIfUserLoggedIn());
        log.info("Log in test via email is finished");
    }

    @Test
    public void checkLogInViaFaceBook(){
        log.info("Log in test via FaceBook is started");
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickOnLoginButton();
        UserHomePage userHomePage = homePage.logInViaFaceBook("vit.radchenko@gmail.com", "iamrealteacher16");
        Assert.assertTrue(userHomePage.checkIfUserLoggedIn());
        log.info("Log in test via FaceBook is finished");
    }

    //Need cause not log out in case FaceBook// Hard to reset
    @AfterMethod
    public void cleanBrowser(){
        UserHomePage userHomePage = new UserHomePage(driver);
        if (userHomePage.checkIfUserLoggedIn()) {
            userHomePage.logOut();
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
        }
    }
}
