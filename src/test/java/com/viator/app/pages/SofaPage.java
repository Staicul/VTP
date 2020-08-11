package com.viator.app.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SofaPage extends AbstractPage {
    @FindBy(css = "button.product-load-more__button")
    private List<WebElementFacade> loadMoreButton;

    @FindBy(tagName = "div")
    private List<WebElementFacade> divList;

    @FindBy(tagName = "p")
    private List<WebElementFacade> pList;

    @FindBy(tagName = "span")
    private List<WebElementFacade> spanList;

    @FindBy(tagName = "body")
    private WebElementFacade body;

    public void clickLoadMoreButton() {
        waitForPageToLoad();
        if (!loadMoreButton.isEmpty()) {
            scrollElementIntoView(loadMoreButton.get(0));
            waitUntilVisibleThenClickWithJs(loadMoreButton.get(0));
            System.out.println("Button clicked successfully.");
        }
    }

    public WebElementFacade getLoadMoreButton() {
        return loadMoreButton.get(0);
    }

    public boolean isButtonPresent() {
        waitForPageToLoad();
        return loadMoreButton.isEmpty();
    }

    public List<String> getAllPageText() {
        waitForPageToLoad();
        List<String> text = new ArrayList<>();
        for (WebElementFacade div : divList) {
            text.add(div.getText());
        }
        for (WebElementFacade span : spanList) {
            text.add(span.getText());
        }
        for (WebElementFacade p : pList) {
            text.add(p.getText());
        }
        return text;
    }

    public String getPageText() {
        return body.getText();
    }
}
