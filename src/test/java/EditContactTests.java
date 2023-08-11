import config.AppiumConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

import java.util.Random;

public class EditContactTests extends AppiumConfig {

    int i = new Random().nextInt(1000) + 1000;

    @BeforeClass
    public void precondition(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("mb@gmail.com")
                .fillPassword("Mb12345$")
                .submitLogin();
    }

    @Test
    public void editOneContactPositive(){
        String text = "Updated_" + i + "@gmail.com";
        new ContactListScreen(driver)
                .editOneContact()
                .editEmail(text)
                .submitEditContact()
                .isContactContains(text);
    }

}