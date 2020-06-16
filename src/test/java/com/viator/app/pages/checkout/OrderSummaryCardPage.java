package com.viator.app.pages.checkout;

import com.viator.app.pages.AbstractPage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class OrderSummaryCardPage extends AbstractPage {

    @FindBy(css = "a[data-action-tag='promo_code_attempt']")
    private WebElementFacade enterPromoCodeLink;

    @FindBy(id = "promoCode")
    private WebElementFacade promoCodeInput;

    @FindBy(id = "promoCodeApplyButton")
    private WebElementFacade applyButton;

    @FindBy(id = "promoCodeError")
    private WebElementFacade errorMessage;

    @FindBy(id = "bookNowBtnRight")
    private WebElementFacade bookNowButton;

    public void clickPromoCodeLink() {
        waitUntilVisibleThenClick(enterPromoCodeLink);
    }

    public void inputPromoCode(String promoCode) {
        clearThenSendKeys(promoCodeInput, promoCode);
    }

    public void clickApplyButton() {
        waitUntilVisibleThenClick(applyButton);
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public void clickBookNowButton() {
        waitUntilVisibleThenClick(bookNowButton);
    }
}
