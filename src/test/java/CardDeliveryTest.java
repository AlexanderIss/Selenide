import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldCardDeliveryTest() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Красноярск");
        $("[name='name']").setValue("Мельник Святослав");

        LocalDate currentDate = LocalDate.now();
        LocalDate inFiveDays = currentDate.plusDays(6);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String text = dtf.format(inFiveDays);
        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(" ");
        $("[placeholder='Дата встречи']").setValue(text);

        $("[name='phone']").setValue("+79344582812");
        $(".checkbox__box").click();
        $(".button__content").click();
        $(".notification__title").shouldHave(Condition.exactText("Успешно!"), Duration.ofSeconds(15));
    }
}
