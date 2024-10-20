package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement waitForAnElementByElementName(WebElement webElement) {
        int DEFAULT_WAIT_TIMEOUT_SECONDS = 10;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void clickToElement(WebElement elementName){
        waitForAnElementByElementName(elementName).click();
    }

    public void sendInput(WebElement elementName, String input){
        waitForAnElementByElementName(elementName).sendKeys(input);
    }

    public WebElement findElementByXpath(String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    public List<WebElement> findElementsByXpath(String xpathExpression) {
        return driver.findElements(By.xpath(xpathExpression));
    }



}
