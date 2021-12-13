package StepDefinitions;

import Utilities.JsonReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import java.util.logging.Logger;

public class LoginSteps {

    WebDriver driver;
    public WebElement element;
    String ObjectProperty, Locator;
    JsonReader jsonReader = new JsonReader();
    public static Logger logger = Logger.getLogger(LoginSteps.class.getName());
    ResourceBundle rb = ResourceBundle.getBundle("TestDetails");

    public enum locator {
        id, xpath
    }

    @Before
    public void init() {
        String browser = rb.getString("Browser");
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:/Users/singh/Drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:/Users/singh/Drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "internetExplorer":
                System.setProperty("webdriver.ie.driver", "C:/Users/singh/Drivers/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            default:
                logger.info("Please provide valid browser");
        }
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Given("I am a new customer")
    public void i_am_a_new_customer() {
        String URL = rb.getString("URL");
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        Assert.assertTrue(driver.getCurrentUrl().equals(URL));
    }


    @Given("access to the MoneyLion website")
    public void access_to_the_money_lion_website() throws Exception {
        Assert.assertTrue(driver.getTitle().contains("Finance App | Mobile Banking"));
        this.capture(driver, "images/HomePage.png");
    }


    @When("I hover on {string} and click on {string} at the top of the webpage")
    public void i_hover_on_about_us_and_click_on_about_us_at_the_top_of_the_webpage(String AboutUs, String subMenu) throws Exception {

        List elements = jsonReader.getProperty(AboutUs);
        ObjectProperty = (String) elements.get(0);
        Locator = (String) elements.get(1);

        WebElement aboutUsMenu = findElementType(ObjectProperty, locator.valueOf(Locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(aboutUsMenu);

        List elements1 = jsonReader.getProperty(subMenu);
        ObjectProperty = (String) elements1.get(0);
        Locator = (String) elements1.get(1);

        WebElement subMenuButton = findElementType(ObjectProperty, locator.valueOf(Locator));
        actions.moveToElement(subMenuButton);
        actions.click().build().perform();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getTitle().contains("What is MoneyLion?"));
        this.capture(driver, "images/AboutUs.png");
        Thread.sleep(5000);
    }


    @Then("I should redirected to the {string} about page")
    public void i_should_redirected_to_the_money_lion_about_page(String MoneyLion) throws Exception {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        List elements = jsonReader.getProperty(MoneyLion);
        ObjectProperty = (String) elements.get(0);
        Locator = (String) elements.get(1);

        WebElement ScrollPage = findElementType(ObjectProperty, locator.valueOf(Locator));
        js.executeScript("arguments[0].scrollIntoView();", ScrollPage);

        List elements1 = jsonReader.getProperty("verifyScrollElement");
        ObjectProperty = (String) elements1.get(0);
        Locator = (String) elements1.get(1);

        WebElement verifyScrollElement = findElementType(ObjectProperty, locator.valueOf(Locator));
        Assert.assertTrue(verifyScrollElement.isDisplayed());
        this.capture(driver, "images/ScrollPage.png");
        Thread.sleep(5000);
    }


    @Then("I should be able to see {string}")
    public void i_should_be_able_to_see_offices_located_in_new_york_san_francisco_salt_lake_city_and_kuala_lumpur(String offices) throws Exception {

        List elements = jsonReader.getProperty(offices);
        ObjectProperty = (String) elements.get(0);
        Locator = (String) elements.get(1);

        WebElement Offices = findElementType(ObjectProperty, locator.valueOf(Locator));

        String cityString = Offices.getText();
        logger.info("The text is: " + cityString);

        this.capture(driver, "images/Offices.png");
        Assert.assertTrue(cityString.contains("Offices located in New York City, San Francisco, Salt Lake City, and Kuala Lumpur."));

        Thread.sleep(5000);
    }


    public void capture(WebDriver webdriver, String path) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(path);
        FileUtils.copyFile(SrcFile, DestFile);
    }


    public WebElement findElementType(String locatorReference, locator locatorType) {

        element = null;
        WebDriverWait wait = new WebDriverWait(driver, 90);

        switch (locatorType) {

            case id:
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorReference)));
                element = driver.findElement(By.id(locatorReference));
                break;

            case xpath:
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorReference)));
                element = driver.findElement(By.xpath(locatorReference));
                break;

            default:
                throw new IllegalArgumentException("Invalid selection method specified !!!");
        }
        return element;

    }


    @After
    public void tearDown() {
        driver.close();
    }

}
