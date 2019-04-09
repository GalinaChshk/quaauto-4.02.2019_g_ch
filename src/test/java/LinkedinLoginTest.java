import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LinkedinLoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Galina\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
              //  { "galina.chshk@gmail.com", "g.analoliivna" },
             //   { "galina.chshk@gmail.com", "g.analoliivna" },
                { " galina.chshk@gmail.com ", "g.analoliivna" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page was not loaded.");

        HomePage homePage = loginPage.login(userEmail, userPassword);

        // HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not loaded.");
    }

    @DataProvider
    public Object[][] emptyValuesProvider() {
        return new Object[][]{
                { "", "" },
                { "galina.chshk@gmail.com", "" },
                { "", "g.analoliivna" }
        };
    }

    @Test(dataProvider = "emptyValuesProvider")
    public void negativeWithEmptyValuesTest(String userEmail, String userPassword) {
        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page was not loaded.");

        loginPage.login(userEmail, userPassword);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
               // { "galina.chshk@gmail.com", "12345", "", "Hmm, that's not the right password. Please try again or request a new one." },
                { "galina.chshk@gmail.com", "g.analoliivna" , "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку", ""},
        };
    }

    @Test(dataProvider = "invalidDataProvider")
    public void negativeNavigatesToLoginSubmitTest(String userEmail,
                                                   String userPassword,
                                                   String emailValidation,
                                                   String passwordValidation) {
        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page was not loaded.");

        loginPage.login(userEmail, userPassword);

        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmit page is not loaded.");

        Assert.assertEquals(loginSubmitPage.getUserEmailValidationMessage(), emailValidation,
                "userEmail validation message is incorrect.");
        Assert.assertEquals(loginSubmitPage.getUserPasswordValidationMessage(),
                passwordValidation,
                "userPassword validation message is incorrect.");
    }
}
