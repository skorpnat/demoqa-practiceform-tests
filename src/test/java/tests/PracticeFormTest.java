package tests;

import data.TestData;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

public class PracticeFormTest extends TestBase {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    TestData testData = new TestData();
    @Test
    void fillFormTest() {

        practiceFormPage.openPage()
                .cleanAdvertisementOnPage()
                .setFirstName(testData.firstName)
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
    }

    @Test
    void inputMinimalData() {
        practiceFormPage.openPage()
                .cleanAdvertisementOnPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setPhoneNumber(testData.phoneNumber)
                .submit();

        practiceFormPage.checkResultTable("Student Name", testData.firstName + " " + testData.lastName)
                .checkResultTable("Gender", testData.gender)
                .checkResultTable("Mobile", testData.phoneNumber);

    }

    @Test
    void inputInvalidMobileNumber() {
        practiceFormPage.openPage()
                .cleanAdvertisementOnPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setPhoneNumber(testData.invalidPhoneNumber)
                .submit();

        practiceFormPage.verifyMobileNumberFieldIsRed();

        }
}
