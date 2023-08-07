package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class AddNewContactScreen extends BaseScreen{

    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement contactlistText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/androidx.appcompat.widget.LinearLayoutCompat")
    MobileElement compactMenuMoreOptions;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement addContactBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/recyclerView']/rowContainer/rowPhone")
    MobileElement contact;


}