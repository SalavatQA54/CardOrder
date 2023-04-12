package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.Date.generateDate;

public class CardOrderTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    String planningDate = generateDate(3, "dd.MM.yyyy");

    @Test
    void positiveRegistrationTest() {

        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys((Keys.CONTROL + "A"), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $("[data-test-id='phone'] input").setValue("+71231231212");
        $(".checkbox__box").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate),
                        Duration.ofSeconds(15))
                .shouldBe(visible);


    }

    @Test
    void invalidCityFieldTest() {

        $("[data-test-id='city'] input").setValue("Анапа");
        $("[data-test-id='date'] input").sendKeys((Keys.CONTROL + "A"), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $("[data-test-id='phone'] input").setValue("+71231231212");
        $(".checkbox__box").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $(".input_invalid[data-test-id='city'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void invalidNameFieldTest() {

        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys((Keys.CONTROL + "A"), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Petrov Petr");
        $("[data-test-id='phone'] input").setValue("+71231231212");
        $(".checkbox__box").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $(".input_invalid[data-test-id='name'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void invalidPhoneFieldTest() {

        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys((Keys.CONTROL + "A"), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $("[data-test-id='phone'] input").setValue("+7123123121");
        $(".checkbox__box").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $(".input_invalid[data-test-id='phone'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void withoutCheckBoxRegistrationTest() {

        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys((Keys.CONTROL + "A"), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $("[data-test-id='phone'] input").setValue("+71231231212");
        $x("//span[contains(text(),'Забронировать')]").click();
        $(".input_invalid[data-test-id='agreement'] .checkbox__text")
                .shouldBe(visible)
                .shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void nullCityFieldTest() {

        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $("[data-test-id='phone'] input").setValue("+71231231212");
        $(".checkbox__box").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $(".input_invalid[data-test-id='city'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
    }


    @Test
    void nullNameFieldTest() {
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys((Keys.CONTROL + "A"), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='phone'] input").setValue("+71231231212");
        $(".checkbox__box").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $(".input_invalid[data-test-id='name'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void nullPhoneFieldTest() {
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys((Keys.CONTROL + "A"), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $(".checkbox__box").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $(".input_invalid[data-test-id='phone'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void cityListClickTest() {

        $("[data-test-id='city'] input").setValue("Вл");
        $x("//span[contains(text(), 'Владимир')]").click();
        $("[data-test-id='date'] input").sendKeys((Keys.CONTROL + "A"), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $("[data-test-id='phone'] input").setValue("+71231231212");
        $(".checkbox__box").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id='notification']")
                .shouldBe(visible,
                        Duration.ofSeconds(15));


    }

    @Test
    void cityListGetElementTest() {

        $("[data-test-id='city'] input").setValue("Вл");
        $$(".menu-item__control").get(3).click();
        $("[data-test-id='date'] input").sendKeys((Keys.CONTROL + "A"), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $("[data-test-id='phone'] input").setValue("+71231231212");
        $(".checkbox__box").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id='notification']")
                .shouldBe(visible,
                        Duration.ofSeconds(15));
    }

    @Test
    void calendarDateTest() {
        int dayToAdd = 7;
        int defaultAddedDays = 3;
        $("[data-test-id='city'] input").setValue("Вл");
        $x("//span[contains(text(), 'Владимир')]").click();
        $("[data-test-id='date'] input").click();

        if(!generateDate(defaultAddedDays,"MM").equals(generateDate(dayToAdd, "MM"))) {
            $("[data-step='1']").click();
        }

        $$(".calendar__day").findBy(text(generateDate(dayToAdd, "d"))).click();
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $("[data-test-id='phone'] input").setValue("+71231231212");
        $(".checkbox__box").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(dayToAdd, "dd.MM.yyyy")),
                        Duration.ofSeconds(15))
                .shouldBe(visible);

    }
}