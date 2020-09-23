package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    @Test
    void shouldCorrectForm() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id='name'] input").setValue("Игорь Попов");
        $("[data-test-id='phone'] input").setValue("+79872229888");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 11000);
        $(byText("Встреча успешно забронирована на"));
        $(byText(date));
    }

//    Способ поиска даты во всплывающем сообщении выбран не очень удачно. В текущей реализации Вы его ищите среди всех
//    элементов страницы, что совсем ненадёжно, дата ведь используется и в поле формы. Лучше делать так
//    $("[data-test-id=notification] .notification__content").shouldHave(text("Встреча успешно забронирована на "+date));

    @Test
    void shouldCorrectForm1() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id='name'] input").setValue("Игорь Попов");
        $("[data-test-id='phone'] input").setValue("+79872229888");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 11000);
        $("[data-test-id=notification] .notification__content").shouldHave(text("Встреча успешно забронирована на "+date));
    }

    @Test
    void shouldTestNotCorrectCity() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Сан-Франциско");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id='name'] input").setValue("Игорь Попов");
        $("[data-test-id='phone'] input").setValue("+79872229888");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldTestNotCorrectDate() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Хабаровск");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id='name'] input").setValue("Игорь Попов");
        $("[data-test-id='phone'] input").setValue("+79872229888");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=date] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldNotCorrectName() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id='name'] input").setValue("Igor Popov");
        $("[data-test-id='phone'] input").setValue("+79872229888");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotCorrectPhone() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id='name'] input").setValue("Игорь Попов");
        $("[data-test-id='phone'] input").setValue("2323232");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldCorrectFormNotAgreement() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id='name'] input").setValue("Игорь Попов");
        $("[data-test-id='phone'] input").setValue("+79872229888");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldTestEmptyForm() {
        open("http://localhost:9999/");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestEmptyCity() {
        open("http://localhost:9999/");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id='name'] input").setValue("Игорь Попов");
        $("[data-test-id='phone'] input").setValue("+79872229888");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestEmptyDate() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Хабаровск");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='name'] input").setValue("Игорь Попов");
        $("[data-test-id='phone'] input").setValue("+79872229888");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=date] .input_invalid .input__sub").shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    void shouldEmptyName() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id='phone'] input").setValue("+79872229888");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyPhone() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Хабаровск");

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $("[data-test-id='date'] input").setValue(date);

        $("[data-test-id='name'] input").setValue("Игорь Попов");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
