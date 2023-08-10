import config.AppiumConfig;
import models.Contact;
import org.slf4j.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

//    int i = new Random().nextInt(1000) + 1000;
    int i = (int) (System.currentTimeMillis() / 1000) % 3600;

    @BeforeMethod
    public void precondition(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("mb@gmail.com")
                .fillPassword("Mb12345$")
                .submitLogin();
    }

    @Test
    public void addNewContactPositive(){

        Contact contact = Contact.builder()
                .name("John" + i)
                .lastName("Snow")
                .email("Josn" + i + "@gmail.com")
                .phone("12345678" + i)
                .address("Tel-Aviv")
                .description("NewPositive")
                .build();

        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Creating a new contact: {}", contact.getName());

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContact()
                .isNewContactPresent(contact.getName());

        logger.info("New contact '{}' was successfully added.", contact.getName());


    }

}