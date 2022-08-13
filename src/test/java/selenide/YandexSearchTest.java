package selenide;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


@RunWith(Parameterized.class)
public class YandexSearchTest {

    private String searchString;

    public YandexSearchTest(String searchString) {
        this.searchString = searchString;
    }


    // будем искать такие слова
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {"selenide"},
                {"футбол"},
                {"бильярд"},
                {"боулинг"}
        };
        return Arrays.asList(data);
    }


    @Test
    public void searchYandex() {
        System.out.println("=== Ищем слово \"" + searchString + "\" ===");
        open("https://yandex.ru");
        $(By.id("text")).setValue(searchString).pressEnter();
//        $("#submit").click();
//        $(".loading_progress").should(disappear); // Само подождёт, пока элемент исчезнет
//        $("#username").shouldHave(text("Hello, Johny!")); // Само подождёт, пока у элемента появится нужный текст

//        String alertText = Selenide.switchTo().alert().getText();
//        Selenide.switchTo().alert().accept();
        System.out.println("=== Первый элемент с текстом = " + $(By.className("extended-text__short")).getText());
        System.out.println("=== Всего по локатору нашли элементов = " + $$(By.className("extended-text__short")).size());

        /*
        for (SelenideElement selenideElement : $$(By.className("extended-text__short"))) {
            System.out.println(selenideElement.text());
            // упадет где нить, т.к. по такому локатору будет и реклама?
            selenideElement.shouldHave(text("selenide"));
        }
        */

        System.out.println("=== Проверим первые 3 выдачи ===");
        ElementsCollection ress = $$(By.className("extended-text__short"));
        for (int i = 0; i < 2; i++) {
            ress.get(i).shouldHave(text(searchString));
        }

    }
}
