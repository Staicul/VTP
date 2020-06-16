package com.viator.app.steps.checkout;

import com.viator.app.pages.checkout.CheckoutPage;
import com.viator.app.pages.checkout.OrderSummaryCardPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

public class CheckoutSteps {

    @Steps
    public CheckoutPage checkoutPage;

    @Steps
    public OrderSummaryCardPage orderSummaryCardPage;

    @Step
    public void fillTravelerInformationForm(String firstName, String lastName, String email, String phone) {
        checkoutPage.inputFirstName(firstName);
        checkoutPage.inputLastName(lastName);
        checkoutPage.inputEmailAddress(email);
        checkoutPage.inputPhone(phone);
    }

    @Step
    public void payWithPayPal() {
        checkoutPage.clickPayWithPayPalButton();
    }

    @Step
    public void payWithGPay() {
        checkoutPage.clickBuyWithGPayButton();
    }

    //Merging all different kinds of errors into a single list
    @Step
    public List<String> getErrorMessages() {
        List<String> errorMessagesText = new ArrayList<>(checkoutPage.getRequiredErrorMessagesText());
        errorMessagesText.addAll(checkoutPage.getPatternErrorMessagesText());
        errorMessagesText.addAll(checkoutPage.getTypeErrorMessagesText());
        errorMessagesText.addAll(checkoutPage.getPhoneErrorMessagesText());
        return errorMessagesText;
    }

    @Step
    public void payWithCreditCard() {
        checkoutPage.clickBookNowButton();
        checkoutPage.waitUntilLoadingScreenNotVisible();
    }

    @Step
    public void addPromoCode(String promoCode) {
        orderSummaryCardPage.clickPromoCodeLink();
        orderSummaryCardPage.inputPromoCode(promoCode);
        orderSummaryCardPage.clickApplyButton();
    }

    @Step
    public String getPromoCodeErrorMessage() {
        return orderSummaryCardPage.getErrorMessageText();
    }
}
