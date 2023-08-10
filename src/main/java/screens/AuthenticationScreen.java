package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AuthenticationScreen extends BaseScreen{


    public AuthenticationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityViewText;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement emailInput;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPassword']")
    MobileElement passwordInput;

    @FindBy(id = "com.sheygam.contactapp:id/regBtn")
    MobileElement regBtn;
    @FindBy(id = "com.sheygam.contactapp:id/loginBtn")
    MobileElement loginBtn;

    public AuthenticationScreen fillEmail(String email){
        waitElement(emailInput, 5);
        type(emailInput, email);
        return this;
    }
    public AuthenticationScreen fillPassword(String password){
        type(passwordInput, password);
        return this;
    }

    public ContactListScreen submitLogin(){
        loginBtn.click();
        pause(3000);
        return new ContactListScreen(driver);
    }
    public ContactListScreen submitRegistration(){
        regBtn.click();
        pause(3000);
        return new ContactListScreen(driver);
    }
    public AuthenticationScreen submitRegistrationNegative() {
        regBtn.click();
        pause(3000);
        return this;
    }
    public AuthenticationScreen isErrorMessageHasText(String text) {
        Assert.assertTrue(isErrorMessageContainsText(text));
        return this;
    }

//    public boolean isAlertPresent(String text) {
//
//        Alert alert = new WebDriverWait(driver, 5)
//                .until(ExpectedConditions.alertIsPresent());
//        if (alert == null) return false;
//        driver.switchTo().alert();
//        System.out.println(alert.getText());
//        alert.accept();
//        return true;
//    }
//    public AuthenticationScreen submitRegistrationNegative2() {
//        regBtn.click();
//        Alert alert = new WebDriverWait(driver, 5)
//                .until(ExpectedConditions.alertIsPresent());
//        if (alert == null) return this;
//        driver.switchTo().alert();
//        System.out.println(alert.getText());
//        alert.accept();
//        return this;
//    }

}