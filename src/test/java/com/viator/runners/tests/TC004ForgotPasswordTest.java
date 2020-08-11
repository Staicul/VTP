package com.viator.runners.tests;

import com.viator.app.pages.AbstractPage;
import com.viator.app.pages.SofaPage;
import com.viator.app.pages.checkout.CheckoutPage;
import com.viator.app.steps.LoginSteps;
import com.viator.app.steps.BookingSteps;
import com.viator.app.steps.SofaSteps;
import com.viator.tools.ConfigUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.jruby.RubyProcess;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@RunWith(SerenityRunner.class)
public class TC004ForgotPasswordTest {

    @Managed
    private WebDriver driver;

    @Steps
    public LoginSteps loginSteps;

    @Steps
    public BookingSteps bookingSteps;

    @Steps
    public SofaSteps sofaSteps;

    private String tourOption = ConfigUtils.getProperty("TOUR_OPTION");

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup(); //takes care of chromedriver for local runs
    }

//    @Before
//    public void setUp() {
//        driver.get(ConfigUtils.getProperty("BASE_URL"));
//        bookingSteps.setNoOfTravelers();
//        bookingSteps.chooseTourOption(tourOption);
//    }

    @Test
    public void tc004ForgotPasswordTest() {
        loginSteps.recoverPassword();
        logger.info("Expected URL to contain 'forgot-password'\nActual URL: " + driver.getCurrentUrl());
        Assert.assertTrue("User was not redirected to the 'Forgot Password' page",
                driver.getCurrentUrl().contains("forgot-password"));
    }

    @Test
    public void scsTest() throws IOException, InterruptedException {
        driver.get("http://scs.co.uk/furniture/");
        int waitCount = 0;

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,2000)");
        while (waitCount < 100) {
            sofaSteps.clickLoadMoreButton();
            waitCount++;
        }
        List<WebElement> linksWithDuplicates = driver.findElements(By.tagName("a"));
        List<WebElement> links = new ArrayList<>(new HashSet<>(linksWithDuplicates));

        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;

        Iterator<WebElement> it = links.iterator();

        while (it.hasNext()) {

            url = it.next().getAttribute("href");

            System.out.println(url);

            if (url == null || url.isEmpty()) {
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }

            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                respCode = huc.getResponseCode();

                if (respCode >= 400) {
                    System.out.println(url + " is a broken link " + respCode);
                } else {
                    System.out.println(url + " is a valid link " + respCode);
                }

            } catch (MalformedURLException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getPageText() {
        driver.get("http://scs.co.uk/help/aftersales-and-care/covid-19.html");
        System.out.println(sofaSteps.getAllText());
    }
}
