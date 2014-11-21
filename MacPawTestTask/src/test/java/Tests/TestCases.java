package Tests;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestCondition;

public class TestCases extends TestCondition {

    @Test
    public void firstTest(){
        HomePage homePage = new HomePage(driver);
        homePage.open();
        SignUpPage signUpPage = homePage.clickOnSignUpNowButton();
        signUpPage.clickOnSellingOutSideCheckBox();
        Assert.assertTrue(signUpPage.checkIfSolutionFormAppeared());
    }

    @Test
    public void secondTest(){
        HomePage homePage = new HomePage(driver);
        homePage.open();
        FrameWorksPage frameWorksPage = homePage.clickOnFeatureButton();
        frameWorksPage.checkSparkleFrameBlock();
        AppManagePage appManagePage = frameWorksPage.clickAppManagementButton();
        Assert.assertTrue(appManagePage.checkIfEasyUpdateBlockPresent());
    }
}
