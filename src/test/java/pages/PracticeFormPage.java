package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.TableComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    CalendarComponent calendarComponent = new CalendarComponent();
    TableComponent tableComponent = new TableComponent();
    public SelenideElement calendar = $("#dateOfBirthInput"),
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            phoneNumber = $("#userNumber"),
            subject = $("#subjectsInput"),
            hobbies = $("#hobbiesWrapper"),
            currentAddress = $("#currentAddress"),
            picture = $("#uploadPicture"),
            stateCityWrapper = $("#stateCity-wrapper"),
            selectState = stateCityWrapper.$("#react-select-3-input"),
            selectCity = stateCityWrapper.$("#react-select-4-input"),
            submit = $("#submit");

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public PracticeFormPage cleanAdvertisementOnPage() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove");
        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public PracticeFormPage setUserEmail(String value) {
        email.setValue(value);
        return this;
    }

    public PracticeFormPage setHobbies(String value) {
        hobbies.$(byText(value)).click();
        return this;
    }
    public PracticeFormPage setSubject(String value) {
        subject.val(value).pressEnter();
        return this;
    }

    public PracticeFormPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    public PracticeFormPage uploadFile(String value) {
        picture.uploadFromClasspath(value);
        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        calendar.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public PracticeFormPage setPhoneNumber(String value) {
        phoneNumber.setValue(value);
        return this;
    }

    public PracticeFormPage setCurrentAddress(String value) {
        currentAddress.setValue(value);
        return this;
    }

    public PracticeFormPage setState(String value) {
        selectState.val(value).pressEnter();
        return this;
    }

    public PracticeFormPage setCity(String value) {
        selectCity.val(value).pressEnter();
        return this;
    }

    public PracticeFormPage submit() {
        submit.click();
        return this;
    }

    public PracticeFormPage checkResultTable(String key, String value) {
        tableComponent.checkResultTableLine(key, value);
        return this;
    }

    public PracticeFormPage verifyMobileNumberFieldIsRed() {
        phoneNumber.shouldHave(Condition.cssValue("border-color", "rgb(220, 53, 69)"));
        return this;
    }
}
