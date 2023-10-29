import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 5000; // default 4000
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("Nata");
        $("#lastName").setValue("Natanova");
        $("#userEmail").setValue("nnatta@test.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("9136754576");

        //выбор даты  из календаря
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1974");
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__day--030").click();

        $("#subjectsInput").val("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#currentAddress").setValue("CurrentAddress str 234");
        $("#uploadPicture").uploadFromClasspath("cat.png");
        $("#state").scrollTo().click();
        $("#react-select-3-input").val("Uttar Pradesh").pressEnter();
        $("#city").click();
        $("#react-select-4-input").val("Lucknow").pressEnter();
        $("#submit").click();

        //Проверка отображения результата
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Nata Natanova"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("nnatta@test.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9136754576"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("30 December,1974"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("cat.png"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("CurrentAddress str 234"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Uttar Pradesh Lucknow"));

    }

    @AfterEach
    void clearAll() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWebDriver();
    }
}
