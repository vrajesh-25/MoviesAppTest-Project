package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccountPage {

    @FindBy(className = "avatar-img")
    WebElement accountImageElement;

    @FindBy(className = "account-heading")
    WebElement accountHeadingElement;

    @FindBy(css = "div[class *= membership-cont] > p")
    WebElement membershipHeadingElement;

    @FindBy(css = "div[class *= membership-details] > p")
    List<WebElement> membershipDetailsElements;

    @FindBy(css = "div[class *= plan-cont] > p")
    WebElement planDetailsHeadingElement;

    @FindBy(css = "div[class *= plan-details-cont] > p")
    List<WebElement> planDetailsElements;

    @FindBy(className = "logout-button")
    WebElement logoutBtnElement;

    WebDriver driver;

    public AccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public WebElement checkAccountLogo(){
        return accountImageElement;
    }

    public String getAccountHeading(){
        return accountHeadingElement.getText();
    }

    public String getMemberShipHeading(){
        return membershipHeadingElement.getText();
    }

    public String getMembershipDetailsAt(int index){
        return membershipDetailsElements.get(index).getText();
    }

    public String getPlanHeading(){
        return planDetailsHeadingElement.getText();
    }

    public String getPlanDetailsAt(int index){
        return planDetailsElements.get(index).getText();
    }

    public void clickLogoutBtn(){
        logoutBtnElement.click();
    }
}
