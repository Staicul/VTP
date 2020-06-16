package com.viator.app.pages.checkout;

import com.viator.app.pages.AbstractPage;
import com.viator.tools.Constants;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CheckoutPage extends AbstractPage {

    @FindBy(css = "input[data-action-prod-attr='traveler_first_name']")
    private WebElementFacade travelerFirstNameInput;

    @FindBy(css = "input[data-action-prod-attr='traveler_last_name']")
    private WebElementFacade travelerLastNameInput;

    @FindBy(id = "contactInfoEmail")
    private WebElementFacade emailAddressInput;

    @FindBy(id = "phoneNumber")
    private WebElementFacade phoneInput;

    @FindBy(css = "div[id='paypalButtonContainer'] div.xcomponent-outlet")
    private WebElementFacade payWithPayPalButton;

    @FindBy(css = "div.parsley-required")
    private List<WebElementFacade> requiredErrorMessageList;

    @FindBy(css = "div.parsley-pattern")
    private List<WebElementFacade> patternErrorMessageList;

    @FindBy(css = "div.parsley-type")
    private List<WebElementFacade> typeErrorMessageList;

    @FindBy(css = "div.parsley-phoneNumber")
    private List<WebElementFacade> phoneErrorMessageList;

    @FindBy(id = "bookNow")
    private WebElementFacade bookNowButton;

    @FindBy(css = "div.checkout-loading-screen")
    private WebElementFacade loadingScreenDiv;

    @FindBy(id = "googlePayButtonContainer")
    private WebElementFacade buyWithGPayButton;

    public void inputFirstName(String firstName) {
        clearThenSendKeys(travelerFirstNameInput, firstName);
    }

    public void inputLastName(String lastName) {
        clearThenSendKeys(travelerLastNameInput, lastName);
    }

    public void inputEmailAddress(String emailAddress) {
        clearThenSendKeys(emailAddressInput, emailAddress);
    }

    public void inputPhone(String phoneNumber) {
        clearThenSendKeys(phoneInput, phoneNumber);
    }


    public void clickPayWithPayPalButton() {
        waitUntilVisibleThenClick(payWithPayPalButton);
    }

    public List<String> getRequiredErrorMessagesText() {
        List<String> errorMessagesText = new ArrayList<>();
        for (WebElementFacade errorMessage : requiredErrorMessageList) {
            errorMessagesText.add(errorMessage.getText());
        }
        return errorMessagesText;
    }

    public List<String> getPatternErrorMessagesText() {
        List<String> errorMessagesText = new ArrayList<>();
        for (WebElementFacade errorMessage : patternErrorMessageList) {
            errorMessagesText.add(errorMessage.getText());
        }
        return errorMessagesText;
    }

    public List<String> getTypeErrorMessagesText() {
        List<String> errorMessagesText = new ArrayList<>();
        for (WebElementFacade errorMessage : typeErrorMessageList) {
            errorMessagesText.add(errorMessage.getText());
        }
        return errorMessagesText;
    }

    public List<String> getPhoneErrorMessagesText() {
        List<String> errorMessagesText = new ArrayList<>();
        for (WebElementFacade errorMessage : phoneErrorMessageList) {
            errorMessagesText.add(errorMessage.getText());
        }
        return errorMessagesText;
    }

    public void clickBookNowButton() {
        int retry = 0;
        while (!loadingScreenDiv.isVisible() && retry < 5) {
            waitUntilVisibleThenClick(bookNowButton);
            retry++;
        }
    }

    public void waitUntilLoadingScreenNotVisible() {
        element(loadingScreenDiv).waitUntilVisible();
        //Was failing intermittently. A longer timeout was needed.
        loadingScreenDiv.withTimeoutOf(Constants.WAIT_TIME, SECONDS).waitUntilNotVisible();
    }

    public void clickBuyWithGPayButton() {
        waitUntilVisibleThenClick(buyWithGPayButton);
    }
}
