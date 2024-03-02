import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MovieDetailsPage;

import java.time.Duration;

public class AccountPageTest {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    AccountPage accountPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RAJESH\\Downloads\\Selinium Testing\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);


        loginPage.loginToWebsite("rahul", "rahul@2021");
        String expUrl ="https://qamoviesapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));

        homePage.clickAccountBtn();
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @Test(priority = 1)
    public void testAccountPageUI(){

        Assert.assertTrue(accountPage.checkAccountLogo().isDisplayed(), "Account Logo not displayed");

        Assert.assertEquals(accountPage.getAccountHeading(), "Account", "Account Heading text does not match");

        Assert.assertEquals(accountPage.getMemberShipHeading(), "Member ship", "Membership Heading text does not match");

        Assert.assertEquals(accountPage.getMembershipDetailsAt(0), "User name : rahul", "Membership Username does not match");

        Assert.assertEquals(accountPage.getMembershipDetailsAt(1), "Password : **********", "Membership Password does not match");

        Assert.assertEquals(accountPage.getPlanHeading(), "Plan details", "Plan Heading does not match");

        Assert.assertEquals(accountPage.getPlanDetailsAt(0), "Premium", "Plan Details Text does not match");

        Assert.assertEquals(accountPage.getPlanDetailsAt(1), "Ultra HD", "Plan Details Text does not match");
    }

    @Test(priority = 2)
    public void testLogoutBtnFunctionality(){

        accountPage.clickLogoutBtn();

        String expUrl = "https://qamoviesapp.ccbp.tech/login";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));

        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expUrl, "Logout Button is disabled");
    }

}
