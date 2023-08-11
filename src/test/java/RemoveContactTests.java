import config.AppiumConfig;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RemoveContactTests extends AppiumConfig {


    @BeforeClass
    public void precondition(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("mb@gmail.com")
                .fillPassword("Mb12345$")
                .submitLogin();
    }

    @BeforeMethod
    public void providerContacts() {
        new ContactListScreen(driver)
                .provideContacts();
    }
    @Test
    public void removeOneContactPositive(){
        Assert.assertTrue(
        new ContactListScreen(driver)
                .removeOneContact()
                .isContactRemoved()
        );
    }

    @Test
    public void removeAllContactsPositive(){
        Assert.assertTrue(
        new ContactListScreen(driver)
                .removeAllContacts()
                .isNoContactsMessage()
        );
    }
}