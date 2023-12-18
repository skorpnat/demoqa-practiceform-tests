package tests;

import data.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import org.junit.jupiter.api.Tag;
import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class PracticeFormTest extends TestBase {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    TestData testData = new TestData();

    @Test
    @DisplayName("Проверка формы со всеми заполненными полями")
    void fillFormTest() {
        step("Открыть форму", () -> {
            practiceFormPage.openPage()
                    .cleanAdvertisementOnPage();
        });

        step("Ввести данные", () -> {
        practiceFormPage.setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setPhoneNumber(testData.phoneNumber)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .setUserEmail(testData.userEmail)
                .setHobbies(testData.hobbies)
                .setSubject(testData.subjects)
                .setCurrentAddress(testData.currentAddress)
                .uploadFile(testData.picture)
                .setState(testData.state)
                .setCity(testData.city)
                .submit();
        });

        step("Проверить отображение данных", () -> {
        practiceFormPage.checkResultTable("Student Name", testData.firstName + " " + testData.lastName)
                .checkResultTable("Student Email", testData.userEmail)
                .checkResultTable("Gender", testData.gender)
                .checkResultTable("Mobile", testData.phoneNumber)
                .checkResultTable("Date of Birth", testData.day + " " + testData.month + "," + testData.year)
                .checkResultTable("Subjects", testData.subjects)
                .checkResultTable("Hobbies", testData.hobbies)
                .checkResultTable("Picture", testData.picture)
                .checkResultTable("Address", testData.currentAddress)
                .checkResultTable("State and City", testData.state + " " + testData.city);
        });
    }

    @Test
    @DisplayName("Проверка формы с минимально заполненными данными")
    void inputMinimalData() {
        step("Открыть форму", () -> {
            practiceFormPage.openPage().cleanAdvertisementOnPage();
        });

        step("Ввести данные", () -> {
            practiceFormPage.setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setGender(testData.gender)
                    .setPhoneNumber(testData.phoneNumber)
                    .submit();
        });

        step("Проверить отображение данных", () -> {
            practiceFormPage.checkResultTable("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResultTable("Gender", testData.gender)
                    .checkResultTable("Mobile", testData.phoneNumber);
        });
    }

    @Test
    @DisplayName("Проверка не валидного номера телефона")
    void inputInvalidMobileNumber() {
        step("Открыть форму", () -> {
            practiceFormPage.openPage()
                    .cleanAdvertisementOnPage();
        });
        step("Ввести данные", () -> {
                    practiceFormPage.setFirstName(testData.firstName)
                            .setLastName(testData.lastName)
                            .setGender(testData.gender)
                            .setPhoneNumber(testData.invalidPhoneNumber)
                            .submit();
                });
        step("Проверить отображение ошибки", () -> {
        practiceFormPage.verifyMobileNumberFieldIsRed();
        });
    }
}
