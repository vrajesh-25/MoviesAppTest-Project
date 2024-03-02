import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;

import java.time.Duration;

public class SearchPageTest {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    SearchPage searchPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RAJESH\\Downloads\\Selinium Testing\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);

        loginPage.loginToWebsite("rahul", "rahul@2021");
        String expUrl ="https://qamoviesapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));
        homePage.clickSearchBtn();
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @DataProvider
    public Object[][] MoviesData(){
        return new Object[][]{
                {"Spider-Man", 3},
                {"Venom", 2},
                {"Godzilla", 2},
                {"Avatar", 1},
                {"Space Jam", 1}
        };
    }

    @Test(priority = 1, dataProvider = "MoviesData")
    public void testSearchFunctionality(String movieName, int expectedCount){
        try{
            searchPage.search(movieName);

            int actualCount = searchPage.getMoviesCount();

            Assert.assertEquals(actualCount, expectedCount, "Movies Count does not match");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test(priority = 2)
    public void testMoviesNotFound(){
        searchPage.search("Avengers");

        Assert.assertTrue(searchPage.findMoviesNotFoundImg().isDisplayed(), "Image not displayed");

        Assert.assertEquals(searchPage.getMoviesNotFoundText(), "Your search for Avengers did not find any matches.");
    }

}
