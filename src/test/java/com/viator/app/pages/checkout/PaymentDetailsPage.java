package com.viator.app.pages.checkout;

import com.viator.app.pages.AbstractPage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class PaymentDetailsPage extends AbstractPage {

    @FindBy(id = "creditCard")
    private WebElementFacade creditCardRadioButton;

    @FindBy(id = "paypal")
    private WebElementFacade payPalRadioButton;

    @FindBy(id = "googlepay")
    private WebElementFacade googlePayRadioButton;

    @FindBy(id = "paymentInfoFullName")
    private WebElementFacade cardholderNameInput;

    @FindBy(id = "creditCard")
    private WebElementFacade cardNumberInput;

    @FindBy(id = "securityCode")
    private WebElementFacade securityCodeInput;

    @FindBy(id = "expiryDateMonth")
    private WebElementFacade expirationMonthSelect;

    @FindBy(id = "expiryDateYear")
    private WebElementFacade expirationYearSelect;

    @FindBy(id = "server-payment-error")
    private WebElementFacade paymentErrorMessage;

    public void clickCreditCardRadioButton() {
        waitUntilVisibleThenClick(creditCardRadioButton);
    }

    public void clickPayPalRadioButton() {
        scrollElementIntoView(payPalRadioButton);
        waitUntilVisibleThenClick(payPalRadioButton);
    }

    public void clickGooglePayRadioButton() {
        waitUntilVisibleThenClick(googlePayRadioButton);
    }

    public void inputCardholderName(String cardholderName) {
        clearThenSendKeys(cardholderNameInput, cardholderName);
    }

    public void inputCardNumber(String cardNumber) {
        cardNumberInput.sendKeys(new String[]{cardNumber});
    }

    public void inputSecurityCode(String securityCode) {
        clearThenSendKeys(securityCodeInput, securityCode);
    }

    public void selectExpirationMonth(String expirationMonth) {
        expirationMonthSelect.select().byValue(expirationMonth);
    }

    public void selectExpirationYear(String expirationYear) {
        expirationYearSelect.select().byValue(expirationYear);
    }

    public String getPaymentErrorMessageText() {
        element(paymentErrorMessage).waitUntilVisible();
        return paymentErrorMessage.getText();
    }
}
