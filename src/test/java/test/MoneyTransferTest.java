package test;

import data.DataHelper;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceOfCard1 = dashboardPage.getCardBalance(0);
        int balanceOfCard2 = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.replenish(1);
        int amount = 2000;
        moneyTransferPage.moneyTransfer(amount, DataHelper.getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(balanceOfCard1 - amount, dashboardPage.getCardBalance(0));
        Assertions.assertEquals(balanceOfCard2 + amount, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldTransferMoneyFromCard1ToCard2ExtraSum() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceOfCard1 = dashboardPage.getCardBalance(0);
        int balanceOfCard2 = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.replenish(1);
        moneyTransferPage.moneyTransfer(balanceOfCard1 + 10000, DataHelper.getFirstCardInfo().getCardNumber());
        moneyTransferPage.errorNotification();
        Assertions.assertEquals(balanceOfCard1, dashboardPage.getCardBalance(0));
        Assertions.assertEquals(balanceOfCard2, dashboardPage.getCardBalance(1));

    }
}
