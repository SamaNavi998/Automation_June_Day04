package pageobjectmodel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PageClothsResults extends PageBase{

    @FindBy(xpath = "//span[text()='adidas']")
    private WebElement adidasCheckBox;

    public PageClothsResults(WebDriver driver) {
        super(driver);
    }

//    @FindBy(xpath = "//span[text()='Samsung']")
//    private WebElement samsungCheckBox;


//    public PageMobileResults(WebDriver driver) {
//        super(driver);
//    }

//    public void clickOnAppleCheckBox(){
//        appleCheckBox.click();
//    }

    public void clickOnAdidasCheckBox(){
        adidasCheckBox.click();
    }

    public void scroll(int x, int y){
        new Actions(driver).scrollByAmount(x, y).perform();
    }
}