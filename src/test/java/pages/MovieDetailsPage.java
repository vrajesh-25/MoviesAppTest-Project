package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MovieDetailsPage {

    @FindBy(css = "div[class *= react] > img[src *= dune]")
    WebElement homeImgElement;

    @FindBy(css = "li[class *= movie] * img[alt = Venom]")
    WebElement popularImgElement;

    @FindBy(css = "div[class *= screen-movie]")
    WebElement homeBackgroundImgElement;

    @FindBy(className = "movie-title")
    WebElement titleElement;

    @FindBy(className = "play-button")
    WebElement playBtnElement;

    @FindBy(className = "genres-heading")
    WebElement genreHeadingElement;

    @FindBy(className = "audio-heading")
    WebElement audioHeadingElement;

    @FindBy(className = "rating-heading")
    WebElement ratingHeadingElement;

    @FindBy(className = "rating-average-heading")
    WebElement ratingAverageElement;

    @FindBy(className = "budget-heading")
    WebElement budgetHeadingElement;

    @FindBy(className = "release-date-heading")
    WebElement releaseDateHeadingElement;

    @FindBy(className = "similar-movies-heading")
    WebElement similarHeadingElement;

    @FindBy(css = "li > img[class = image-style]")
    List<WebElement> similarMoviesImgElements;

    WebDriver driver;
    WebDriverWait wait;

    public MovieDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickHomePageMovie(){
        homeImgElement.click();
    }

    public void clickPopularPageMovie(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[class *= movie] * img[alt = Venom]")));
        popularImgElement.click();
    }

    public WebElement checkBackgroundImage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class *= screen-movie]")));
        return homeBackgroundImgElement;
    }

    public String getMovieTitle(){
        return titleElement.getText();
    }

    public WebElement checkPlayBtn(){
        return playBtnElement;
    }

    public String getGenreHeading(){
        return genreHeadingElement.getText();
    }

    public String getAudioHeading(){
        return audioHeadingElement.getText();
    }

    public String getRatingHeading(){
        return ratingHeadingElement.getText();
    }

    public String getRatingAverage(){
        return ratingAverageElement.getText();
    }

    public String getBudgetHeading(){
        return budgetHeadingElement.getText();
    }

    public String getReleaseDate(){
        return releaseDateHeadingElement.getText();
    }

    public String getSimilarHeading(){
        return similarHeadingElement.getText();
    }

    public int getSimilarMoviesNum(){
        return similarMoviesImgElements.size();
    }

}
