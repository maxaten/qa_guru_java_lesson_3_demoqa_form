import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LoginForm {
    String uri = "/automation-practice-form";
    String firstName = "Bruce";
    String lastName = "Wayne";
    String fullName = firstName + " " + lastName;
    String email = "wayneinterprise@waine.com";
    String gender = "Male";
    String phoneNumber = "7999999999";
    String subject1 = "Physics";
    String subject2 = "Computer Science";
    String hobbie1 = "Sports";
    String hobbie2 = "Reading";
    String hobbie3 = "Music";
    String address = "1007 Mountain Drive, Gotham";
    String state = "Haryana";
    String city = "Karnal";
    String summitText = "Thanks for submitting the form";


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillForm() {
        open(uri);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('#app footer').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker").$("[value='11']").click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").$("[value='1990']").click();
        $(".react-datepicker__day--010").click();

        $("#subjectsInput").setValue(subject1).pressEnter();
        $("#subjectsInput").setValue(subject2).pressEnter();

        $("#hobbiesWrapper").$(byText(hobbie1)).click();
        $("#hobbiesWrapper").$(byText(hobbie2)).click();
        $("#hobbiesWrapper").$(byText(hobbie3)).click();

        $("#uploadPicture").uploadFromClasspath("bruce.jpeg");
        $("#currentAddress").setValue(address);

        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text(summitText));
        $("tbody").shouldHave(exactText("Student Name " + fullName + "\n" +
                                                    "Student Email " + email + "\n" +
                                                    "Gender " + gender + "\n" +
                                                    "Mobile " + phoneNumber + "\n" +
                                                    "Date of Birth 10 December,1990" + "\n" +
                                                    "Subjects " + subject1 + ", " + subject2 + "\n" +
                                                    "Hobbies " + hobbie1 + ", " + hobbie2 + ", " + hobbie3 + "\n" +
                                                    "Picture bruce.jpeg" + "\n" +
                                                    "Address " + address + "\n" +
                                                    "State and City " + state + " " + city));
    }

    @AfterAll
    static void tearDown(){
        Selenide.closeWebDriver();
    }
}
