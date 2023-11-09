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

        practiceFormPage.checkResultTableLine("Student Name", "Nata Natanova")
                .checkResultTableLine("Student Email", "nnatta@test.com")
                .checkResultTableLine("Gender", "Female")
                .checkResultTableLine("Mobile", "9136754576")
                .checkResultTableLine("Date of Birth", "30 December,1974")
                .checkResultTableLine("Subjects", "Maths")
                .checkResultTableLine("Hobbies", "Reading")
                .checkResultTableLine("Picture", "cat.png")
                .checkResultTableLine("Address", "CurrentAddress str 234")
                .checkResultTableLine("State and City", "Uttar Pradesh Lucknow");
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

        practiceFormPage.checkResultTableLine("Student Name", "Nata Natanova")
                .checkResultTableLine("Gender", "Female")
                .checkResultTableLine("Mobile", "9136754576");

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
