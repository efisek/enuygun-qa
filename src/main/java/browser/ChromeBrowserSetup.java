package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowserSetup implements BrowserSetup{
    @Override
    public WebDriver setupDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
