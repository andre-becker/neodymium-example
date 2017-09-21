/**
 * 
 */
package com.xceptance.neodymium.scripting.template.selenide.page.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import com.xceptance.neodymium.scripting.template.selenide.objects.User;
import com.xceptance.neodymium.scripting.template.selenide.page.BasicPage;

/**
 * @author pfotenhauer
 */
public class PRegister extends BasicPage
{

    /*
     * (non-Javadoc)
     * 
     * @see com.xceptance.neodymium.scripting.template.selenide.page.PageObject#isExpectedPage()
     */
    @Override
    public boolean isExpectedPage()
    {
        return $("#formRegister").exists();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.xceptance.neodymium.scripting.template.selenide.page.PageObject#validateStructure()
     */
    @Override
    public void validateStructure()
    {
        // Login headline
        // Make sure the Headline is there and starts with a capital letter followed by at least 3 more symbols.
        $("#formRegister .h2").should(matchText("[A-Z].{3,}"));
        // Form
        // Asserts the label belonging to the last name field displays the correct text
        $("label[for=\"lastName\"]").shouldHave(exactText("Last name*"));
        // Make sure the field to type in the last name is visible.
        $("#lastName").shouldBe(visible);
        // Asserts the label belonging to the first name field displays the correct text
        $("label[for=\"firstName\"]").shouldHave(exactText("First name*"));
        // Make sure the field to type in the first name is visible.
        $("#firstName").shouldBe(visible);
        // Asserts the label belonging to the email field displays the correct text
        $("label[for=\"eMail\"]").shouldHave(exactText("Email address*"));
        // Make sure the field to type in the e-Mail is visible.
        $("#eMail").shouldBe(visible);
        // Asserts the label belonging to the password field displays the correct text
        $("label[for=\"password\"]").shouldHave(exactText("Password*"));
        // Make sure the field to type in the password is visible.
        $("#password").shouldBe(visible);
        // Asserts the label belonging to the second password field displays the correct text
        $("label[for=\"passwordAgain\"]").shouldHave(exactText("Repeat password*"));
        // Make sure the field to type in the password aggain is visible.
        $("#passwordAgain").shouldBe(visible);
        // Register button
        // Make sure the Registration button displays the correct text.
        $("#btnRegister").shouldHave(exactText("Create account"));
    }

    /**
     * @param email
     *            The email of the account you want to log into
     * @param password
     *            The password of the account you want to log into
     */
    public PLogin sendRegisterForm(String firstName, String lastName, String email, String password, String passwordRepeat)
    {
        // Fill out the registration form
        // Type the last name parameter into the last name field.
        $("#lastName").val(lastName);
        // Type the first name parameter into the first name field.
        $("#firstName").val(firstName);
        // Type the email parameter into the email field.
        $("#eMail").val(email);
        // Type the password parameter into the password field.
        $("#password").val(password);
        // Type the second password parameter into the second password field.
        $("#passwordAgain").val(password);
        // Register and open the login page if successful
        // Click on the Register Button
        $("#btnRegister").click();

        return page(PLogin.class);
    }

    /**
     * @param user
     * @param passwordRepeat
     * @return
     */
    public PLogin sendRegisterForm(User user, String passwordRepeat)
    {
        return sendRegisterForm(user.getFirstName(), user.getLastName(), user.getEMail(), user.getPassword(), passwordRepeat);
    }
}