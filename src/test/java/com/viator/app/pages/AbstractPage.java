package com.viator.app.pages;

import com.viator.tools.Constants;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Set;

public abstract class AbstractPage extends PageObject {

    protected void waitUntilVisibleThenClick(WebElement element) {
        element(element).waitUntilVisible();
        element.click();
    }

    protected void scrollElementIntoView(WebElement element) {
        evaluateJavascript("arguments[0].scrollIntoView(true);", element);
    }

    protected void clearThenSendKeys(WebElement element, String string) {
        element(element).waitUntilVisible();
        element.clear();
        element.sendKeys(string);
    }

    public void switchToLatestWindow() {
        String currentWindowHandle = getDriver().getWindowHandle();
        Set<String> windowHandles = getDriver().getWindowHandles();
        for (String string : windowHandles) {
            if (!string.contains(currentWindowHandle)) {
                getDriver().switchTo().window(string);
                break;
            }
        }
    }

    public void waitForPageToLoad() {
        int retry = 0;
        String response = "";
        do {
            try {
                Thread.sleep(Constants.RETRY_WAIT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response = String.valueOf(((JavascriptExecutor) getDriver()).executeScript("return document.readyState"));
            retry++;
        } while (retry <= Constants.PAGE_LOAD_MAX_RETRY && response.equals("complete") != true);
    }
}
