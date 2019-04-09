import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSubmitPage {
    private WebDriver driver;

    private WebElement loginSubmitForm;

    private WebElement userEmailValidationMessage;
    private WebElement userPasswordValidationMessage;

    public LoginSubmitPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        loginSubmitForm = driver.findElement(By.xpath("//form[@action='/checkpoint/lg/login-submit']"));
        userEmailValidationMessage = driver.findElement(By.xpath("//div[@id='error-for-username']"));
        userPasswordValidationMessage = driver.findElement(By.xpath("//div[@id='error-for-password']"));
    }

    public boolean isPageLoaded() {
        return loginSubmitForm.isDisplayed();
    }

    public String getUserEmailValidationMessage() {
        return userEmailValidationMessage.getText();
    }

    public String getUserPasswordValidationMessage() {
        return userPasswordValidationMessage.getText();
    }
}
