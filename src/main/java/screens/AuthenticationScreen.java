package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

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

}