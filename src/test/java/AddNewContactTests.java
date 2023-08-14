import config.AppiumConfig;
import models.Contact;
import org.slf4j.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
    public void addNewContactPositive() {

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

        Assert.assertTrue(
                new ContactListScreen(driver)
                        .openContactForm()
                        .fillContactForm(contact)
                        .submitContact()
                        .isContactAdded(contact)
        );
    }

    @Test() //invocationCount = 20
    public void addNewContactPositiveScroll(){

        Contact contact = Contact.builder()
                .name("Din" + i)
                .lastName("Djarin")
                .email("mandalorian" + i + "@gmail.com")
                .phone("1234567" + i)
                .address("Mandalor")
                .description("This is the way")
                .build();

            new ContactListScreen(driver)
                    .openContactForm()
                    .fillContactForm(contact)
                    .submitContact()
                    .isContactAddedScroll(contact);
    }

//    @AfterMethod
//    public void postcondition(){
//        if(new ContactListScreen(driver).isContactListActivityPresent()){
//            new ContactListScreen(driver).logout();
//            new SplashScreen(driver);
//        }
//    }

}

