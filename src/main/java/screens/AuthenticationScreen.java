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
    @FindBy(id = "android:id/message")
    MobileElement errorTextView;
    @FindBy(id = "android:id/button1")
    MobileElement okBtn;

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

//    public boolean isErrorMessageHasText(String text){
////       return errorTextView.getText().contains(text);
//    return  isErrorMessageContainsText(text);
//    }
    public AuthenticationScreen isErrorMessageHasText(String text) {
        Assert.assertTrue(isErrorMessageContainsText(text));
        return this;
    }



}