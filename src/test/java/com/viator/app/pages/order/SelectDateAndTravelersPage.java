package com.viator.app.pages.order;

import com.viator.app.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectDateAndTravelersPage extends AbstractPage {

    @FindBy(css = "div[data-action-tag='click_paxselector_open']")
    private WebElement noOfTravelersDiv;

    @FindBy(id = "applyPaxButton")
    private WebElement noOfTravelersApplyButton;

    @FindBy(id = "paxModal")
    private WebElement noOfTravelersModal;

    public void clickOnNumberOfTravelersDiv() {
        waitUntilVisibleThenClick(noOfTravelersDiv);
        element(noOfTravelersModal).waitUntilVisible();
    }

    public void clickApplyButton() {
        waitUntilVisibleThenClick(noOfTravelersApplyButton);
        element(noOfTravelersModal).waitUntilNotVisible();
    }
}
