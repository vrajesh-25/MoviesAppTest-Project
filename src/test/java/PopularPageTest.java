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
import pages.PopularPage;

import java.time.Duration;
import java.util.List;

public class PopularPageTest {

    WebDriver driver;
    LoginPage loginPage;
    PopularPage popularPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RAJESH\\Downloads\\Selinium Testing\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");

        popularPage = new PopularPage(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        loginPage.loginToWebsite("rahul", "rahul@2021");
        String expUrl ="https://qamoviesapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));
        homePage.clickPopularPage();
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @Test
    public void testPopularPageUI(){

        try{
            List<WebElement> movieImageElements = driver.findElements(By.cssSelector("li[class = 'movie-icon-item'] * img"));


            for(WebElement movieImage : movieImageElements){
                Assert.assertTrue(movieImage.isDisplayed(), "Movie Image not Displayed");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
