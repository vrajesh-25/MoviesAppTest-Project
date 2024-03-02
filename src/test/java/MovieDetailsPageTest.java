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
import pages.MovieDetailsPage;

import java.time.Duration;
import java.util.List;

public class MovieDetailsPageTest {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    MovieDetailsPage movieDetailsPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RAJESH\\Downloads\\Selinium Testing\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        movieDetailsPage = new MovieDetailsPage(driver);


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
    public void testMovieDetailsHomePage(){
        homePage.clickHomePage();

        movieDetailsPage.clickHomePageMovie();
        String expUrl = "https://qamoviesapp.ccbp.tech/movies/c6ef2389-078a-4117-b2dd-1dee027e5e8e";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));

        Assert.assertTrue(movieDetailsPage.checkBackgroundImage().isDisplayed(), "Background image not displayed");

        Assert.assertEquals(movieDetailsPage.getMovieTitle(), "Dune", "Movie title does not match");

        Assert.assertTrue(movieDetailsPage.checkPlayBtn().isEnabled(), "Play Button is disabled");

        Assert.assertEquals(movieDetailsPage.getGenreHeading(), "Genres", "Genre Heading does not match");

        Assert.assertEquals(movieDetailsPage.getAudioHeading(), "Audio Available", "Audio Heading does not match");

        Assert.assertEquals(movieDetailsPage.getRatingHeading(), "Rating Count", "Rating Heading does not match");

        Assert.assertEquals(movieDetailsPage.getRatingAverage(), "Rating Average", "Rating Avearage does not match");

        Assert.assertEquals(movieDetailsPage.getBudgetHeading(), "Budget", "Budget Heading does not match");

        Assert.assertEquals(movieDetailsPage.getReleaseDate(), "Release Date", "Release Date Heading does not match");

        Assert.assertEquals(movieDetailsPage.getSimilarHeading(), "More like this");

        List<WebElement> similarMoviesList = driver.findElements(By.cssSelector("li > img[class = image-style]"));

        for(WebElement movieImg : similarMoviesList){
            wait.until(ExpectedConditions.visibilityOf(movieImg));

            Assert.assertTrue(movieImg.isDisplayed(), "Similar movies not displayed");
        }
    }

    @Test(priority = 2)
    public void testMovieDetailsPopularPage(){
        homePage.clickPopularPage();

        movieDetailsPage.clickPopularPageMovie();
        String expUrl = "https://qamoviesapp.ccbp.tech/movies/320dee56-fdb2-40cf-8df8-92b251bd781f";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));

        Assert.assertTrue(movieDetailsPage.checkBackgroundImage().isDisplayed(), "Background image not displayed");

        Assert.assertEquals(movieDetailsPage.getMovieTitle(), "Venom", "Movie title does not match");

        Assert.assertTrue(movieDetailsPage.checkPlayBtn().isEnabled(), "Play Button is disabled");

        Assert.assertEquals(movieDetailsPage.getGenreHeading(), "Genres", "Genre Heading does not match");

        Assert.assertEquals(movieDetailsPage.getAudioHeading(), "Audio Available", "Audio Heading does not match");

        Assert.assertEquals(movieDetailsPage.getRatingHeading(), "Rating Count", "Rating Heading does not match");

        Assert.assertEquals(movieDetailsPage.getRatingAverage(), "Rating Average", "Rating Avearage does not match");

        Assert.assertEquals(movieDetailsPage.getBudgetHeading(), "Budget", "Budget Heading does not match");

        Assert.assertEquals(movieDetailsPage.getReleaseDate(), "Release Date", "Release Date Heading does not match");

        Assert.assertEquals(movieDetailsPage.getSimilarHeading(), "More like this");

        List<WebElement> similarMoviesList = driver.findElements(By.cssSelector("li > img[class = image-style]"));

        for(WebElement movieImg : similarMoviesList){
            wait.until(ExpectedConditions.visibilityOf(movieImg));

            Assert.assertTrue(movieImg.isDisplayed(), "Similar movies not displayed");
        }

    }
}
