package selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


@RunWith(Parameterized.class)
public class YandexSearchSelenoidTest {

    private final String searchString;
    private final String browser;

    Logger logger = LoggerFactory.getLogger(YandexSearchSelenoidTest.class);

    public YandexSearchSelenoidTest(String searchString, String browser) {
        this.searchString = searchString;
        this.browser = browser;
    }


    // will search the following words
    @Parameterized.Parameters(name = "{index}: Selenoid browser = {1}, search str = {0}")
    public static Collection<Object[]> data() {
//        String browser = Configuration.browser;
        Object[][] data = new Object[][]{
                {"selenide", BrowserEnum.CHROME.getName()},
                {"футбол", BrowserEnum.CHROME.getName()},
                {"буйа", BrowserEnum.CHROME.getName()},
                {"бильярд", BrowserEnum.FIREFOX.getName()},
                {"боулинг", BrowserEnum.FIREFOX.getName()},
                {"opera", BrowserEnum.OPERA.getName()}
        };
        return Arrays.asList(data);
    }


    /*@Before
    public void init() {
//        String browserName = capabilities.getBrowserName();
    }*/

    @Before
    public void setBrowser() {
        Configuration.browser = this.browser;
//        >mvn test -Dtest=selenide/* -Dselenide.browser=firefox
        Configuration.remote = "http://igor-virtualbox:4444/wd/hub";
//        Configuration.browser = "chrome";
//        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

    }

    @Test
    public void searchYandex() {
        logger.info("=== Searching: \"" + searchString + "\" in " + browser + " ===");
        open("https://yandex.ru");
        $(By.id("text")).setValue(searchString).pressEnter();
//        $("#submit").click();
//        $(".loading_progress").should(disappear); // Само подождёт, пока элемент исчезнет
//        $("#username").shouldHave(text("Hello, Johny!")); // Само подождёт, пока у элемента появится нужный текст

//        String alertText = Selenide.switchTo().alert().getText();
//        Selenide.switchTo().alert().accept();
        logger.info("=== First element: " + $(By.className("extended-text__short")).getText());
        logger.info("=== Found by locator: " + $$(By.className("extended-text__short")).size());

        /*
        for (SelenideElement selenideElement : $$(By.className("extended-text__short"))) {
            System.out.println(selenideElement.text());
            // упадет где нить, т.к. по такому локатору будет и реклама?
            selenideElement.shouldHave(text("selenide"));
        }
        */

        logger.info("=== Check first 3 outs ===");
        ElementsCollection ress = $$(By.className("extended-text__short"));
        for (int i = 0; i < 2; i++) {
            ress.get(i).shouldHave(text(searchString));
        }

        if (searchString.equalsIgnoreCase("буйа")){
            $(By.className("extended-text__short")).shouldHave(text("AAAAAAA"));
        }

    }

    @After
    public void tearDown() {
        // close browser - we run all test in the same browser?
        WebDriverRunner.getWebDriver().close();
    }
}
