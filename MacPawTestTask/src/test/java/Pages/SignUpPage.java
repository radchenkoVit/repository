package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WebDriverWrapper;

public class SignUpPage extends BasePage {
    public SignUpPage(WebDriverWrapper driver) {
        super(driver);
    }

    private static By sellingOutSideCheckBox = By.name("selling_outside_app_store");
    private static By solutionInput = By.name("solution");

    public void clickOnSellingOutSideCheckBox(){
        driver.findElement(sellingOutSideCheckBox).click();
        waitForConditions.until(ExpectedConditions.elementToBeSelected(sellingOutSideCheckBox));
    }

    public boolean checkIfSolutionFormAppeared(){
        if (driver.findElements(solutionInput).size() > 0)
            return true;
        return false;
    }
}
