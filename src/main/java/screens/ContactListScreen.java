package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.slf4j.*;
import org.testng.Assert;


import java.util.List;
import java.util.Random;

public class ContactListScreen extends BaseScreen {

    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    String phoneNumber;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityViewText;

    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement moreOptions;

    @FindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    MobileElement addContactBtn;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logoutBtn;

    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement yesBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emptyTxt']")
    MobileElement emptyTxt;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contacts;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> names;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> phones;


    public boolean isContactListActivityPresent() {
        return shouldHave(activityViewText, "Contact list", 5);
    }

    public AuthenticationScreen logout() {
        moreOptions.click();
        logoutBtn.click();
        return new AuthenticationScreen(driver);
    }

    public AddNewContactScreen openContactForm() {
        waitElement(addContactBtn, 5);
        addContactBtn.click();
        return new AddNewContactScreen(driver);
    }

    public boolean isContactAdded(Contact contact) {
        boolean checkName = checkContainsText(names, contact.getName() + " " + contact.getLastName());
        boolean checkPhone = checkContainsText(phones, contact.getPhone());
//        Assert.assertTrue(checkName && checkPhone);

        return checkName && checkPhone;
    }

    public boolean checkContainsText(List<MobileElement> list, String text) {

        for (MobileElement element : list) {
            if (element.getText().contains(text)) return true;
        }
        return false;
    }

    public ContactListScreen removeOneContact() {
        waitElement(addContactBtn, 5);
        MobileElement contact = contacts.get(0);
        Rectangle rect = contact.getRect();
        phoneNumber = phones.get(0).getText();

        int xStart = rect.getX() + rect.getWidth() / 8;
        int xEnd = xStart + rect.getWidth() * 6 / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xStart, y))
                .moveTo(PointOption.point(xEnd, y))
                .release()
                .perform();

        waitElement(yesBtn, 5);
        yesBtn.click();
        pause(3000);
        return this;
    }

    //    public ContactListScreen isContactRemoved(){
//        Assert.assertFalse(phones.contains(phoneNumber));
//        return this;
//    }
    public boolean isContactRemoved() {
        boolean res = phones.contains(phoneNumber);
        return !res;
    }

    public ContactListScreen removeAllContacts() {
        waitElement(addContactBtn, 5);
        while (contacts.size() > 0) {
            removeOneContact();
        }
        return this;
    }

    public boolean isNoContactsMessage() {
        return shouldHave(emptyTxt, "No Contacts. Add One more!", 5);
    }

    public ContactListScreen provideContacts() {
        while (contacts.size() < 3) {
            addContact();
        }
        return this;
    }

    public void addContact() {


        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("John" + i)
                .lastName("Snow")
                .email("Josn" + i + "@gmail.com")
                .phone("12345678" + i)
                .address("Tel-Aviv")
                .description("NewPositive")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContact();
    }

    public EditContactScreen editOneContact(){
        waitElement(addContactBtn, 5);
        MobileElement contact = contacts.get(0);
        Rectangle rect = contact.getRect();

        int xEnd = rect.getX() + rect.getWidth() / 8;
        int xStart = xEnd + (rect.getWidth() * 6) / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xStart, y))
                .moveTo(PointOption.point(xEnd, y))
                .release()
                .perform();

        pause(3000);
        return new EditContactScreen(driver);
    }

    public boolean isContactContains(String text){
        contacts.get(0).click();
        Contact contact = new ViewContactScreen(driver).viewContactObject();
        driver.navigate().back();
        return contact.toString().contains(text);
    }


}