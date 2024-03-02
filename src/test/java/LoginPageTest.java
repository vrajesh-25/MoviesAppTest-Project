import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginPageTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RAJESH\\Downloads\\Selinium Testing\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @Test(priority = 1)
    public void testloginPageUI(){
        Assert.assertTrue(loginPage.findLoginPageLogo().isDisplayed(), "Website Login Logo Not Displayed");

        Assert.assertEquals(loginPage.getLoginHeading(), "Login", "Login heading text does not match");

        Assert.assertEquals(loginPage.getLabelTextAt(0), "USERNAME", "UserName label text does not match");

        Assert.assertEquals(loginPage.getLabelTextAt(1), "PASSWORD", "Password label text does not match");

        Assert.assertTrue(loginPage.checkLoginBtn().isEnabled(), "Login Button is disabled");
    }

    @Test(priority = 2)
    public void testLoginWithEmptyInputs(){
        loginPage.clickOnLoginBtn();

        Assert.assertEquals(loginPage.getErrorMessage(), "*Username or password is invalid", "Error text with empty input fields does not match");
    }

    @Test(priority = 3)
    public void testLoginWithEmptyUserName(){
        loginPage.loginToWebsite("", "rahul@2021");

        Assert.assertEquals(loginPage.getErrorMessage(), "*Username or password is invalid", "Error text with empty username does not match");
    }

    @Test(priority = 4)
    public void testLoginWithEmptyPassword(){
        loginPage.loginToWebsite("rahul", "");

        Assert.assertEquals(loginPage.getErrorMessage(), "*Username or password is invalid", "Error text with empty password does not match");
    }

    @Test(priority = 5)
    public void testLoginWithInvalidCredentials(){
        loginPage.loginToWebsite("rahul", "rahul");

        Assert.assertEquals(loginPage.getErrorMessage(), "*username and password didn't match", "Error text with invalid password does not match");
    }


    @Test(priority = 6)
    public void testLoginWithValidCredentials(){
        loginPage.loginToWebsite("rahul", "rahul@2021");

        String expectedUrl = "https://qamoviesapp.ccbp.tech/";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl, "Url's do not match");
    }
}
