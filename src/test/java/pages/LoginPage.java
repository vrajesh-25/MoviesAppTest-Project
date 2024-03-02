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

public class LoginPage {

    @FindBy(className = "login-website-logo")
    WebElement loginLogoElement;

    @FindBy(className = "sign-in-heading")
    WebElement loginTextElement;

    @FindBy(className = "input-label")
    List<WebElement> labelElements;

    @FindBy(className = "login-button")
    WebElement loginBtnElement;

    @FindBy(id = "usernameInput")
    WebElement userNameInputElement;

    @FindBy(id =  "passwordInput")
    WebElement passwordInputElement;

    @FindBy(className = "error-message")
    WebElement errorMessageElement;

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement findLoginPageLogo(){
        return loginLogoElement;
    }

    public String getLoginHeading(){
        return loginTextElement.getText();
    }

    public String getLabelTextAt(int index){
        return labelElements.get(index).getText();
    }

    public void enterUserName(String username){
        userNameInputElement.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordInputElement.sendKeys(password);
    }

    public WebElement checkLoginBtn(){
        return loginBtnElement;
    }

    public void clickOnLoginBtn(){
        loginBtnElement.click();
    }

    public void loginToWebsite(String username, String password){
        enterUserName(username);
        enterPassword(password);
        clickOnLoginBtn();
    }

    public String getErrorMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        return errorMessageElement.getText();
    }
}
