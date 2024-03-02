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

public class SearchPage {

    @FindBy(id = "search")
    WebElement inputElement;

    @FindBy(className = "search-button")
    WebElement searchBtnElement;

    @FindBy(css = "li[class = 'movie-icon-item'] * img")
    List<WebElement> movieListElements;

    @FindBy(css = "div[class *= 'not-found'] > img")
    WebElement notFoundImgElement;

    @FindBy(css = "div[class *= 'not-found'] > p")
    WebElement notFoundTextElement;

    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterSearchText(String searchText){
        inputElement.sendKeys(searchText);
    }

    public void clickSearchBtn(){
        searchBtnElement.click();
    }

    public void search(String searchText){
        enterSearchText(searchText);
        clickSearchBtn();

    }

    public int getMoviesCount(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[class = 'movie-icon-item'] * img")));
        return movieListElements.size();
    }

    public WebElement findMoviesNotFoundImg(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class *= 'not-found'] > img")));
        return notFoundImgElement;
    }

    public String getMoviesNotFoundText(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class *= 'not-found'] > p")));
        return notFoundTextElement.getText();
    }
}
