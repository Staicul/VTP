package com.viator.app.pages.order;

import com.viator.app.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookingOptionsPage extends AbstractPage {

    @FindBy(css = "label[id^='tour-option']")
    private List<WebElement> optionLabelList;

    @FindBy(id = "showMoreTourOptionsButton")
    private List<WebElement> showMoreOptionsButtonList;

    public boolean isShowMoreOptionsButtonPresent() {
        return !showMoreOptionsButtonList.isEmpty();
    }

    public void clickShowMoreOptionsButton() {
        WebElement showMoreOptionsButton = showMoreOptionsButtonList.get(0);
        scrollElementIntoView(showMoreOptionsButton);
//        Scroll needed as click on showMoreOptionsButton gets intercepted by the footer. Other element would receive
//        the click: <div class="btn btn-block btn-outline-primary reserve-now-pay-later m-0 d-none d-sm-inline-block"...
        waitUntilVisibleThenClick(showMoreOptionsButton);
    }

    public void clickOnOptionBookButton(String option) {
        for (WebElement optionLabel : optionLabelList) {
            if (optionLabel.findElement(By.cssSelector("div.option-pill")).getText().equalsIgnoreCase(option)) {
                optionLabel.click();
                optionLabel.findElement(By.cssSelector("div[data-automation='add-to-cart-button']")).click();
                break;
            }
        }
    }
}
