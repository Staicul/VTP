package com.viator.app.steps.checkout;

import com.viator.app.pages.checkout.PaymentDetailsPage;
import com.viator.app.pages.checkout.CheckoutPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class PaymentSteps {

    @Steps
    public PaymentDetailsPage paymentDetailsPage;

    @Steps
    public CheckoutPage checkoutPage;

    @Step
    public void selectPayPalPaymentMethod() {
        paymentDetailsPage.clickPayPalRadioButton();
    }

    @Step
    public void selectGPayPaymentMethod() {
        paymentDetailsPage.clickGooglePayRadioButton();
    }

    @Step
    public void fillCardInformationForm(String cardholder, String cardNumber, String expirationMonth, String expirationYear, String securityCode) {
        paymentDetailsPage.inputCardholderName(cardholder);
        getDriver().switchTo().frame("checkoutCDEIFrame");
        paymentDetailsPage.inputCardNumber(cardNumber);
        paymentDetailsPage.selectExpirationMonth(expirationMonth);
        paymentDetailsPage.selectExpirationYear(expirationYear);
        paymentDetailsPage.inputSecurityCode(securityCode);
        getDriver().switchTo().defaultContent();
    }

    public String getPaymentErrorText() {
        return paymentDetailsPage.getPaymentErrorMessageText();
    }

    public String getNewlyOpenedWindowTitle() {
        paymentDetailsPage.switchToLatestWindow();
        return getDriver().getTitle();
    }
}
