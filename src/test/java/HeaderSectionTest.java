import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class HeaderSectionTest {

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RAJESH\\Downloads\\Selinium Testing\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);


        loginPage.loginToWebsite("rahul", "rahul@2021");
        String expUrl ="https://qamoviesapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @Test(priority = 1)
    public void checkHeaderSectionUI(){
        Assert.assertTrue(homePage.checkHomePageLogo().isDisplayed(), "Website Logo not displayed");

        Assert.assertEquals(homePage.getNavbarHomeText(), "Home", "Header text does not match");

        Assert.assertEquals(homePage.getNavbarPopularText(), "Popular", "Header text does not match");

        Assert.assertTrue(homePage.checkSearchBtn().isEnabled(), "Search button is disabled");

        Assert.assertTrue(homePage.checkAccountBtn().isEnabled(), "Account button is disabled");
    }

    @Test(priority = 2)
    public void testNavigationToPopularPage(){
        homePage.clickPopularPage();

        String expUrl = "https://qamoviesapp.ccbp.tech/popular";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));

        String popularPageUrl = driver.getCurrentUrl();

        Assert.assertEquals(popularPageUrl, expUrl, "Navigation to Popular page failed");
    }

    @Test(priority = 3)
    public void testNavigationToHomePage(){
        homePage.clickHomePage();

        String expUrl = "https://qamoviesapp.ccbp.tech/";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));

        String popularPageUrl = driver.getCurrentUrl();

        Assert.assertEquals(popularPageUrl, expUrl, "Navigation to Home page failed");
    }

    @Test(priority = 4)
    public void testNavigationToSearchBtn(){
        homePage.clickSearchBtn();

        String expUrl = "https://qamoviesapp.ccbp.tech/search";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));

        String popularPageUrl = driver.getCurrentUrl();

        Assert.assertEquals(popularPageUrl, expUrl, "Navigation to Search page failed");
    }

    @Test(priority = 5)
    public void testNavigationToAccountPage(){
        homePage.clickAccountBtn();

        String expUrl = "https://qamoviesapp.ccbp.tech/account";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));

        String popularPageUrl = driver.getCurrentUrl();

        Assert.assertEquals(popularPageUrl, expUrl, "Navigation to Account page failed");

    }
}
