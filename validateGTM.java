package AdvantageMedicarePlans;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class validateGTM
{
    WebDriver driver;
    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws InterruptedException
    {
        // Setup WebDriver for different browsers
        if(browser.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else
        if(browser.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else
        if(browser.equalsIgnoreCase("edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://advantage-medicare-plans.com/");
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void validate_Load_Of_GTM()
    {
        boolean isGtmLoaded = driver.getPageSource().contains("googletagmanager.com");
        Assert.assertTrue(isGtmLoaded, "GTM is not loaded on the page!");
    }

    @Test(priority = 2)
    public void validate_GTM()
    {
        WebElement gtmElement = driver.findElement(By.xpath("//script[contains(@src,'googletagmanager.com/gtm.js')]"));
        String gtmSrc = gtmElement.getAttribute("src");
        System.out.println("Google Tag Manager URL: " + gtmSrc);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}