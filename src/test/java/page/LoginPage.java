package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement loginField =  $x("//span[@data-test-id='login']//input");
    private SelenideElement passwordField =  $x("//span[@data-test-id='password']//input");
    private SelenideElement loginButton = $x("//*[@data-test-id='action-login']/span/span");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}
