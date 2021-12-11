package personal.project.two_vago;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TwoVagoApplicationTests {
    public  static WebDriver webDriver;

    public static Random random = new Random();
    public static String automationName;
    public static String automationEmail;
    String automationAge = "420";
    String automationPassword = "1234";
    String automationNumber = "0886969";

    By NAV_REGISTER_BUTTON = By.xpath("//*[text()='Register']");
    By NAV_LOGIN_BUTTON = By.xpath("//*[text()='Login']");
    By NAV_PROFILE_BUTTON = By.xpath("//*[text()='Profile']");

    By NAV_OFFERS_BUTTON = By.xpath("//*[text()='Offers']");
    By NAV_ADD_OFFER_BUTTON = By.xpath("//*[text()='Add Offer']");

    By USERNAME_FIELD = By.cssSelector("[id=username]");
    By FULL_NAME_REGISTER_FIELD = By.cssSelector("[id=fullName]");
    By AGE_REGISTER_FIELD = By.cssSelector("[id=age]");
    By EMAIL_REGISTER_FIELD = By.cssSelector("[id=email]");
    By NUMBER_REGISTER_FIELD = By.cssSelector("[id=number]");
    By PASSWORD_FIELD = By.cssSelector("[id=password]");
    By PASSWORD_FIELD2 = By.cssSelector("[id=psw]");

    By OFFER_NAME = By.cssSelector("[id=offerName]");
    By OFFER_PRICE = By.cssSelector("[id=price]");
    By OFFER_PICTURE = By.cssSelector("[id=picture]");
    By OFFER_DESCRIPTION = By.cssSelector("[id=description]");
    By OFFER_CATEGORY = By.name("category");
    By OFFER_CITY = By.name("city");

    By CONFIRM_PASSWORD_REGISTER_FIELD = By.cssSelector("[id=confPassword]");
    By SUBMIT_BUTTON_REGISTER = By.cssSelector("[class=loginbtn]");
    By PROFILE_NAME = By.cssSelector("[id=name]");
    By OFFER = By.cssSelector("section[class=new-deal]");

    By OFFER_DELETE = By.cssSelector("[id=delete]");
    By OFFER_UPDATE = By.cssSelector("a[id=update]");
    By OFFER_TITLE = By.cssSelector("h3[class='product-title']");


    By PROFILE_PAGE_USERNAME = By.cssSelector("[id=name]");

    @BeforeAll
    public static void variablesSetup(){
        automationName = "automation" + random.nextInt(99999);
        automationEmail = automationName + "@abv.bg";
    }

    @BeforeEach
    public void setup(){
        if(webDriver != null){
         webDriver.quit();
        }
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://localhost:8080");
    }

    @Order(1)
    @Test
    void registrationOfANewUser(){
        click(NAV_REGISTER_BUTTON);
        webDriver.findElement(USERNAME_FIELD).sendKeys(automationName);
        webDriver.findElement(FULL_NAME_REGISTER_FIELD).sendKeys(automationName);
        webDriver.findElement(AGE_REGISTER_FIELD).sendKeys(automationAge);
        webDriver.findElement(EMAIL_REGISTER_FIELD).sendKeys(automationEmail);
        webDriver.findElement(NUMBER_REGISTER_FIELD).sendKeys(automationNumber);
        webDriver.findElement(PASSWORD_FIELD).sendKeys(automationPassword);
        webDriver.findElement(CONFIRM_PASSWORD_REGISTER_FIELD).sendKeys(automationPassword);
        click(SUBMIT_BUTTON_REGISTER);
        waitUntilVisible(NAV_PROFILE_BUTTON, 5);
        click(NAV_PROFILE_BUTTON);
        waitUntilVisible(PROFILE_PAGE_USERNAME, 5);
        String name = webDriver.findElement(PROFILE_PAGE_USERNAME).getText();
        Assert.assertEquals(automationName, name);
    }

    @Order(2)
    @Test
    void loginWithRegisteredUser(){
        click(NAV_LOGIN_BUTTON);
        login();
        click(NAV_PROFILE_BUTTON);
        waitUntilVisible(PROFILE_NAME, 5);
        String shownName = webDriver.findElement(PROFILE_NAME).getText();
        Assert.assertEquals(automationName, shownName);
    }

    @Order(3)
    @Test
    void addingAnOffer(){
        click(NAV_OFFERS_BUTTON);
        login();
        int offersAvailable = webDriver.findElements(OFFER).size();
        click(NAV_ADD_OFFER_BUTTON);
        webDriver.findElement(OFFER_NAME).sendKeys("Test Offer" + automationName);
        webDriver.findElement(OFFER_PRICE).sendKeys(automationAge);
        webDriver.findElement(OFFER_PICTURE).sendKeys("https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Cogwheel.png/1200px-Cogwheel.png");
        webDriver.findElement(OFFER_DESCRIPTION).sendKeys("Test Offer" + automationName);
        Select dropdown = new Select(webDriver.findElement(OFFER_CATEGORY));
        dropdown.selectByVisibleText("HOTEL");
        dropdown = new Select(webDriver.findElement(OFFER_CITY));
        dropdown.selectByVisibleText("Sofia");
        click(SUBMIT_BUTTON_REGISTER);
        click(NAV_OFFERS_BUTTON);
        waitUntilVisible(OFFER, 2);
        int offersAvailableAfterAdding = webDriver.findElements(OFFER).size();
        Assert.assertTrue(offersAvailable + 1 == offersAvailableAfterAdding);
    }

    @Order(4)
    @Test
    void updatingAnOffer(){
        click(NAV_OFFERS_BUTTON);
        login();
        List<WebElement> offers = webDriver.findElements(OFFER);
        int offersCount = offers.size();
        String lastOfferId = offers.get(offersCount - 1).getAttribute("id");
        webDriver.get("http://localhost:8080/offers/" + lastOfferId + "/details");
        String originalOfferName = webDriver.findElement(OFFER_TITLE).getText();
        click(OFFER_UPDATE);
        waitUntilVisible(OFFER_NAME, 8);
        webDriver.findElement(OFFER_NAME).clear();
        webDriver.findElement(OFFER_NAME).sendKeys("TESTAUTO");
        webDriver.findElement(SUBMIT_BUTTON_REGISTER).click();
        String updatedOfferName = webDriver.findElement(OFFER_TITLE).getText();
        Assert.assertNotEquals(originalOfferName, updatedOfferName);
        Assert.assertTrue(updatedOfferName.equals("TESTAUTO"));
    }

    @Order(5)
    @Test
    void deletingAnOffer(){
        click(NAV_OFFERS_BUTTON);
        login();
        List<WebElement> offersOriginally = webDriver.findElements(OFFER);
        int offersOriginallyCount = offersOriginally.size();
        String lastOfferId = offersOriginally.get(offersOriginallyCount - 1).getAttribute("id");
        webDriver.get("http://localhost:8080/offers/" + lastOfferId + "/details");
        click(OFFER_DELETE);
        int availableOffersAfterDeletion = webDriver.findElements(OFFER).size();
        Assert.assertTrue(offersOriginallyCount == availableOffersAfterDeletion + 1);
    }

    @AfterAll
    private static void cleanUp(){
        webDriver.quit();
    }

    public void click(By selector){
        webDriver.findElement(selector).click();
    }

    public void waitUntilVisible(By selector, int seconds){
        WebElement element = (new WebDriverWait(webDriver, seconds))
                .until(ExpectedConditions.elementToBeClickable(selector));
        element.click();
    }

    public void login(){
        webDriver.findElement(USERNAME_FIELD).sendKeys(automationName);
        webDriver.findElement(PASSWORD_FIELD2).sendKeys(automationPassword);
        click(SUBMIT_BUTTON_REGISTER);
    }
}