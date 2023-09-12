import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class loginForm {


    String firstName = "Bruce";
    String lastName = "Wayne";
    String fullName = firstName + " " + lastName;
    String email = "wayneinterprise@waine.com";
    String phoneNumber = "7999999999";
    String subject1 = "Physics";
    String subject2 = "Computer Science";
    String address = "1007 Mountain Drive, Gotham";
    String summitText = "Thanks for submitting the form";



    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillForm() {
        open("");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('#app footer').remove()");


        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[for=gender-radio-1]").click();
        $("#userNumber").setValue(phoneNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $("[value='11']").click();
        $(".react-datepicker__year-select").click();
        $("[value='1990']").click();
        $(".react-datepicker__day--010").click();


        $("#subjectsInput").setValue(subject1).pressEnter();
        $("#subjectsInput").setValue(subject2).pressEnter();


        $("[for='hobbies-checkbox-1']").click();
        $("[for='hobbies-checkbox-2']").click();
        $("[for='hobbies-checkbox-3']").click();

        $("#uploadPicture").uploadFromClasspath("bruce.jpeg");

        $("#currentAddress").setValue(address);

        $("#state").click();
        $("#react-select-3-option-1").click();
        $("#city").click();
        $("#react-select-4-option-1").click();

        $("#submit").click();


        $("#example-modal-sizes-title-lg").shouldHave(text(summitText));
        $("tbody").shouldHave(exactText("Student Name " + fullName + "\n" +
                                                    "Student Email " + email + "\n" +
                                                    "Gender Male\n" +
                                                    "Mobile " + phoneNumber + "\n" +
                                                    "Date of Birth 10 December,1990" + "\n" +
                                                    "Subjects " + subject1 + ", " + subject2 + "\n" +
                                                    "Hobbies Sports, Reading, Music\n" +
                                                    "Picture bruce.jpeg" + "\n" +
                                                    "Address " + address + "\n" +
                                                    "State and City Uttar Pradesh Lucknow"));
    }

    @Test
     void closeSummitForm(){
        $("#closeLargeModal").click();
        $("body").shouldNotHave(text("Thanks for submitting the form"));

    }


    @AfterAll
    static void tearDown(){
        Selenide.closeWebDriver();
    }





}
