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

public class HomePage {

    @FindBy(className = "movies-list-heading")
    List<WebElement> headingTextsElements;

    @FindBy(className = "home-movie-play-button")
    WebElement playBtnElement;

    @FindBy(className = "poster")
    List<WebElement> movieImagesElements;

    @FindBy(className = "contact-us-paragraph")
    WebElement contactTextElement;

    @FindBy(className = "website-logo")
    WebElement homeWebsiteLogoElement;

    @FindBy(linkText = "Home")
    WebElement navbarHomeElement;

    @FindBy(linkText = "Popular")
    WebElement navbarPopularElement;

    @FindBy(className = "search-empty-button")
    WebElement navbarSearchBtnElement;

    @FindBy(className = "avatar-button")
    WebElement navbarAccountBtnElement;

    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getHeadingTextAt(int index){
        return headingTextsElements.get(index).getText();
    }

    public WebElement checkPlayButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("home-movie-play-button")));
        return playBtnElement;
    }

    public String getContactText(){
        return contactTextElement.getText();
    }

    public WebElement checkHomePageLogo(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class = 'nav-content'] * img")));
        return homeWebsiteLogoElement;
    }

    public String getNavbarHomeText(){
        return navbarHomeElement.getText();
    }

    public String getNavbarPopularText(){
        return navbarPopularElement.getText();
    }

    public WebElement checkSearchBtn(){
        return navbarSearchBtnElement;
    }

    public WebElement checkAccountBtn(){
        return navbarAccountBtnElement;
    }

    public void clickPopularPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Popular")));
        navbarPopularElement.click();
    }

    public void clickHomePage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));
        navbarHomeElement.click();
    }

    public void clickSearchBtn(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-empty-button")));
        navbarSearchBtnElement.click();
    }

    public void clickAccountBtn(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("avatar-button")));
        navbarAccountBtnElement.click();
    }
}
