package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {


        private SelenideElement codeField = $x("//span[@data-test-id='code']//span/input");
        private SelenideElement verifyButton = $x("//*[@data-test-id='action-verify']/span/span");

        public VerificationPage() {
            codeField.shouldBe(visible);
        }

        public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
            codeField.setValue(verificationCode.getCode());
            verifyButton.click();
            return new DashboardPage();
        }
}
