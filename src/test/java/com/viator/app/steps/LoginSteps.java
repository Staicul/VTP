package com.viator.app.steps;

import com.viator.app.pages.checkout.CollapsibleLoginPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class LoginSteps {

    @Steps
    public CollapsibleLoginPage collapsibleLoginPage;

    @Step
    public void logInWithCredentials(String email, String password) {
        collapsibleLoginPage.waitForPageToLoad(); //needed as it takes a while for the page to be ready
        collapsibleLoginPage.clickSignInLink();
        collapsibleLoginPage.inputEmail(email);
        collapsibleLoginPage.inputPassword(password);
        collapsibleLoginPage.clickLoginButton();
    }

    @Step
    public String getLoginSuccessMessage() {
        return collapsibleLoginPage.getSignInSuccessMessageText();
    }

    @Step
    public String getLoginErrorMessage() {
        return collapsibleLoginPage.getLoginErrorMessageText();
    }

    @Step
    public void recoverPassword() {
        collapsibleLoginPage.waitForPageToLoad();
        collapsibleLoginPage.clickSignInLink();
        collapsibleLoginPage.clickForgotPasswordLink();
        collapsibleLoginPage.switchToLatestWindow();
    }
}
