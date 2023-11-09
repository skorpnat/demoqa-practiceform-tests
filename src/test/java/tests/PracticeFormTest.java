package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

public class PracticeFormTest extends TestBase {
    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Test
    void fillFormTest() {
        practiceFormPage.openPage()
                .cleanAdvertisementOnPage()
                .setFirstName("Nata")
                .setLastName("Natanova")
                .setGender("Female")
                .setPhoneNumber("9136754576")
                .setDateOfBirth("30", "December", "1974")
                .setUserEmail("nnatta@test.com")
                .setHobbies("Reading")
                .setSubject("Math")
                .setCurrentAddress("CurrentAddress str 234")
                .uploadFile("cat.png")
                .setState("Uttar Pradesh")
                .setCity("Lucknow")
                .submit();

        practiceFormPage.checkResultTable("Student Name", "Nata Natanova")
                .checkResultTable("Student Email", "nnatta@test.com")
                .checkResultTable("Gender", "Female")
                .checkResultTable("Mobile", "9136754576")
                .checkResultTable("Date of Birth", "30 December,1974")
                .checkResultTable("Subjects", "Maths")
                .checkResultTable("Hobbies", "Reading")
                .checkResultTable("Picture", "cat.png")
                .checkResultTable("Address", "CurrentAddress str 234")
                .checkResultTable("State and City", "Uttar Pradesh Lucknow");
    }

    @Test
    void inputMinimalData() {
        practiceFormPage.openPage()
                .cleanAdvertisementOnPage()
                .setFirstName("Nata")
                .setLastName("Natanova")
                .setGender("Female")
                .setPhoneNumber("9136754576")
                .submit();

        practiceFormPage.checkResultTable("Student Name", "Nata Natanova")
                .checkResultTable("Gender", "Female")
                .checkResultTable("Mobile", "9136754576");

    }

    @Test
    void inputInvalidMobileNumber() {
        practiceFormPage.openPage()
                .cleanAdvertisementOnPage()
                .setFirstName("Nata")
                .setLastName("Natanova")
                .setGender("Female")
                .setPhoneNumber("4576")
                .submit();

        practiceFormPage.verifyMobileNumberFieldIsRed();

        }
}
