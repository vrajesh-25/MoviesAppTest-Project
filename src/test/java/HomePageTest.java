import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.util.List;

public class HomePageTest {

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
    public void testHeadingTexts(){
        Assert.assertEquals(homePage.getHeadingTextAt(0), "Trending Now", "Heading text does not match");

        Assert.assertEquals(homePage.getHeadingTextAt(1), "Originals", "Heading text does not match");
    }

    @Test(priority = 2)
    public void testPlayButton(){
        Assert.assertTrue(homePage.checkPlayButton().isEnabled(), "Play button is disabled");
    }

    @Test(priority = 3)
    public void testMoviesDisplay(){
        try{
            List<WebElement> moviesList = driver.findElements(By.cssSelector("div[class = 'react-slick-item'] > img"));

            for(WebElement movieImage : moviesList){
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOf(movieImage));

                Assert.assertTrue(movieImage.isDisplayed(), "Movie Image not displayed");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    @Test(priority = 4)
    public void testContactSection(){
        Assert.assertEquals(homePage.getContactText(), "Contact Us", "Contact Text does not match");
        Assert.assertFalse(homePage.getContactText().isEmpty(), "Failed");
    }
}
