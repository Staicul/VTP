package com.viator.app.steps;

import com.viator.app.pages.SofaPage;
import com.viator.app.pages.checkout.CollapsibleLoginPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.JavascriptExecutor;

public class SofaSteps {
    @Steps
    public SofaPage sofaPage;

    @Step
    public void clickLoadMoreButton() {
        sofaPage.clickLoadMoreButton();
    }

    public boolean isButtonPresent() {
        return sofaPage.isButtonPresent();
    }

    public String getPageText() {
        return sofaPage.getPageText();
    }

    public String getAllText() {
        String text = "";
        for (String txt: sofaPage.getAllPageText()) {
            text = text.concat("\n" + txt);
        };
        return text;
    }
}
