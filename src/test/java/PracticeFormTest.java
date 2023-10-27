import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Nata");
        $("#lastName").setValue("Natanova");
        $("#userEmail").setValue("nnatta@test.com");
        $x("//label[@for='gender-radio-2']").click();
        $("#userNumber").setValue("9136754576");

        //выбор даты  из календаря
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1974");
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__day--030").click();

        $("#subjectsInput").val("Math").pressEnter();
        $x("//label[@for='hobbies-checkbox-2']").click();
        $("#currentAddress").setValue("CurrentAddress str 234");
        $("#uploadPicture").uploadFile(new File("src/test/resources/cat.png"));
        $("#state").scrollTo().click();
        $("#react-select-3-input").val("Uttar Pradesh").pressEnter();
        $("#city").click();
        $("#react-select-4-input").val("Lucknow").pressEnter();//.pressTab().pressEnter();

        $("#react-select-4-input").pressEnter();
        $("#submit").click();

        //Проверка отображения результата
        $x("//tbody//tr[1]//td[2]").shouldHave(text("Nata Natanova"));
        $x("//tbody//tr[2]//td[2]").shouldHave(text("nnatta@test.com"));
        $x("//tbody//tr[3]//td[2]").shouldHave(text("Female"));
        $x("//tbody//tr[4]//td[2]").shouldHave(text("9136754576"));
        $x("//tbody//tr[5]//td[2]").shouldHave(text("30 December,1974"));
        $x("//tbody//tr[6]//td[2]").shouldHave(text("Maths"));
        $x("//tbody//tr[7]//td[2]").shouldHave(text("Reading"));
        $x("//tbody//tr[8]//td[2]").shouldHave(text("cat.png"));
        $x("//tbody//tr[9]//td[2]").shouldHave(text("CurrentAddress str 234"));
        $x("//tbody//tr[10]//td[2]").shouldHave(text("Uttar Pradesh Lucknow"));
        $("#closeLargeModal").click();

    }

    @AfterEach
    void clearAll() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWebDriver();
    }
}
