package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PopularPage {

    @FindBy(linkText = "Popular")
    WebElement popularPageElement;

    @FindBy(css = "li[class = 'movie-icon-item'] * img")
    List<WebElement> moviesListElements;

    WebDriver driver;

    public PopularPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnPopularLink(){
        popularPageElement.click();
    }

    public int getNoOfMovies(){
        return moviesListElements.size();
    }
}
