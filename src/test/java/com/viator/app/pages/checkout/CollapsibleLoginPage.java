package com.viator.app.pages.checkout;

import com.viator.app.pages.AbstractPage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class CollapsibleLoginPage extends AbstractPage {

    @FindBy(css = "div[id='loginContent'] a")
    private WebElementFacade signInLink;

    @FindBy(id = "loginEmail")
    private WebElementFacade emailInput;

    @FindBy(id = "password")
    private WebElementFacade passwordInput;

    @FindBy(id = "loginButton")
    private WebElementFacade loginButton;

    @FindBy(id = "signInSuccess")
    private WebElementFacade signInSuccessMessage;

    @FindBy(css = "a.small[href='/forgot-password']")
    private WebElementFacade forgotPasswordLink;

    @FindBy(id = "loginError")
    private WebElementFacade loginErrorMessage;

    public void clickSignInLink() {
        waitUntilVisibleThenClick(signInLink);
    }

    public void inputEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void inputPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        waitUntilVisibleThenClick(loginButton);
    }

    public String getSignInSuccessMessageText() {
        return signInSuccessMessage.getText();
    }

    public void clickForgotPasswordLink() {
        waitUntilVisibleThenClick(forgotPasswordLink);
    }

    public String getLoginErrorMessageText() {
        return loginErrorMessage.getText();
    }
}
