package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import pageObjects.Career_Page_Objects;
import pageObjects.Quality_Assurance_Page_Objects;
import pageObjects.Welcome_Page_Objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;

    public Welcome_Page_Objects welcome_page_objects;

    public Career_Page_Objects career_page_objects;

    public Quality_Assurance_Page_Objects quality_assurance_objects;

    public Properties properties;
    FileInputStream fileInputStream;

    public WebDriver getDriver() {
        return driver;
    }

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) throws Exception {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless");
            //options.addArguments("--no-sandbox");
            //options.addArguments("--disable-dev-shm-usage");
            //options.addArguments("window-size=1200,800");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        } else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            //options.addArguments("--headless");
            //options.addArguments("--no-sandbox");
            //options.addArguments("--disable-dev-shm-usage");
            //options.addArguments("window-size=1200,800");
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();

        } else{
            throw new Exception("Desteklenmeyen browser : "+browser);
        }

    }
    @BeforeMethod(alwaysRun = true)
    public void methodSetup() throws IOException {
        getReadPropFile();

        welcome_page_objects = new Welcome_Page_Objects(driver);
        career_page_objects = new Career_Page_Objects(driver);
        quality_assurance_objects = new Quality_Assurance_Page_Objects(driver);

    }


    public void getReadPropFile() throws IOException {
        properties = new Properties();
        fileInputStream = new FileInputStream("src/test/resources/config.properties");
        properties.load(fileInputStream);
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (driver != null){
            driver.quit();
        }
    }
}
