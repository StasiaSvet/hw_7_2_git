package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTest {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.startMaximized = true;

    }

    @Test
    void fillInTheFormTest() {

        String firstName = "Ivan";
        String lastName = "Ivanov";
        String email = "ivanivanov@vvv.com";
        String userNumber = "3334445556";
        String subjectInput1 = "English";
        String subjectInput2 = "Biology";
        String cAddress = "ABC 123 KKK";
        String state = "Rajasthan";
        String city = "Jaiselmer";

        open("automation-practice-form");

        //---Data input
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText("Male")).click();
        $("#userNumber").setValue(userNumber);

        //---Calendar date picker
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("3");
        $(".react-datepicker__year-select").selectOptionByValue("1986");
        $(".react-datepicker__day.react-datepicker__day--002").click();

        //---Subjects
        $("#subjectsInput").setValue(subjectInput1).pressEnter();
        $("#subjectsInput").setValue(subjectInput2).pressEnter();

        //---Hobby
        $(byText("Sports")).click();
        $(byText("Reading")).click();

        //---Uploading picture
        $("#uploadPicture").uploadFromClasspath("111.jpg");

        //---Address
        $("#currentAddress").setValue(cAddress);
        $("#state").scrollIntoView(true);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        //Select by input
        //$("#react-select-3-input").setValue("Haryana").pressEnter();
        //$("#react-select-4-input").setValue("Panipat").pressEnter();

        $("#submit").click();

        //---Checking the data in the modal form
        $(".modal-content").shouldHave(

                text(firstName + lastName),
                text(email),
                text("Male"),
                text(userNumber),
                text("02 April,1986"),
                text(subjectInput1 + ", " + subjectInput2),
                text("Sports, Reading"),
                text("111.jpg"),
                text(cAddress),
                text(state + " " + city));
        //Sleep(5000);
    }

}
