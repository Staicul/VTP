package com.viator.app.steps;

import com.viator.app.pages.order.BookingOptionsPage;
import com.viator.app.pages.checkout.CollapsibleLoginPage;
import com.viator.app.pages.checkout.CheckoutPage;
import com.viator.app.pages.order.SelectDateAndTravelersPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class BookingSteps {

    @Steps
    public SelectDateAndTravelersPage selectDateAndTravelersPage;

    @Steps
    public BookingOptionsPage bookingOptionsPage;

    @Steps
    public CheckoutPage checkoutPage;

    @Steps
    public CollapsibleLoginPage collapsibleLoginPage;

    @Step
    public void setNoOfTravelers() {
        selectDateAndTravelersPage.clickOnNumberOfTravelersDiv();
        selectDateAndTravelersPage.clickApplyButton();
    }

    @Step
    public void chooseTourOption(String option) {
        if (bookingOptionsPage.isShowMoreOptionsButtonPresent()) {
            bookingOptionsPage.clickShowMoreOptionsButton();
        }
        bookingOptionsPage.waitForPageToLoad();
        bookingOptionsPage.clickOnOptionBookButton(option);
    }
}

