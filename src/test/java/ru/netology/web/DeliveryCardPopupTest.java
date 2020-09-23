package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardPopupTest {

    @Test
    void shouldViewListOfCity() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Мо");
        $$(".menu-item .menu-item__control").find(exactText("Кемерово")).click();

        $("[data-test-id='city'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='city'] input").setValue("Мо");
        $$(".menu-item .menu-item__control").find(exactText("Майкоп")).click();

        $("[data-test-id='city'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='city'] input").setValue("Мо");
        $$(".menu-item .menu-item__control").find(exactText("Москва")).click();

        $("[data-test-id='city'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='city'] input").setValue("Мо");
        $$(".menu-item .menu-item__control").find(exactText("Симферополь")).click();

        $("[data-test-id='city'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='city'] input").setValue("Мо");
        $$(".menu-item .menu-item__control").find(exactText("Смоленск")).click();

        $("[data-test-id='city'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='city'] input").setValue("Мо");
        $$(".menu-item .menu-item__control").find(exactText("Тамбов")).click();
    }

    @Test
    void shouldSelectDateNextWeek() {
        open("http://localhost:9999");
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfDelivery = LocalDate.now().plusDays(7);
        String day = dateOfDelivery.format(DateTimeFormatter.ofPattern("d"));
        $(".input__icon").click();
       if (dateOfDelivery.getMonthValue() - currentDate.getMonthValue() == 1) {
            $("[data-step='1']").click();
        }
        $$("td.calendar__day").find(exactText(day)).click();
    }

    @Test
    void shouldCorrectForm() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Ха");
        $$(".menu-item .menu-item__control").find(exactText("Хабаровск")).click();

        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfDelivery = LocalDate.now().plusDays(7);
        String date = dateOfDelivery.format(DateTimeFormatter.ofPattern("d"));
        $(".input__icon").click();
        if (dateOfDelivery.getMonthValue() - currentDate.getMonthValue() == 1) {
            $("[data-step='1']").click();
        }
        $$("td.calendar__day").find(exactText(date)).click();

        $("[data-test-id='name'] input").setValue("Игорь Попов");
        $("[data-test-id='phone'] input").setValue("+79872229888");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 11000);
        $(byText("Встреча успешно забронирована на"));

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateDelivery = formatter1.format(dateOfDelivery);
        $(byText(dateDelivery));
    }
}
