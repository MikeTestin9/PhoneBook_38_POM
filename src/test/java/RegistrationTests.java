import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {

    int i = new Random().nextInt(1000) + 1000;

    @Test
    public void registrationPositive() {

        Assert.assertTrue(
                new SplashScreen(driver)
                        .gotoAuthenticationScreen()
                        .fillEmail("mb_" + i + "@gmail.com")
                        .fillPassword("Mb12345$")
                        .submitRegistration()
                        .isContactListActivityPresent()
        );
    }

    @Test
    public void registrationNegativeWrongEmail() {

        Assert.assertTrue(
                new SplashScreen(driver)
                        .gotoAuthenticationScreen()
                        .fillEmail("mb_" + i + "gmail.com")
                        .fillPassword("Mb12345$")
                        .submitRegistration()
                        .isContactListActivityPresent()
        );
    }
    @Test
    public void registrationNegativeWrongEmailHW() {

        new AuthenticationScreen(driver)
                .fillEmail("mb_" + i + "gmail.com")
                .fillPassword("Mb12345$")
                .submitRegistrationNegative()
                .isErrorMessageHasText("{username=must be a well-formed email address}");
    }


}
