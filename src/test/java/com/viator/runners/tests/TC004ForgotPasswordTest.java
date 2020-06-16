package com.viator.runners.tests;

import com.viator.app.steps.LoginSteps;
import com.viator.app.steps.BookingSteps;
import com.viator.tools.ConfigUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SerenityRunner.class)
public class TC004ForgotPasswordTest {

    @Managed
    private WebDriver driver;

    @Steps
    public LoginSteps loginSteps;

    @Steps
    public BookingSteps bookingSteps;

    private String tourOption = ConfigUtils.getProperty("TOUR_OPTION");

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup(); //takes care of chromedriver for local runs
    }

    @Before
    public void setUp() {
        driver.get(ConfigUtils.getProperty("BASE_URL"));
        bookingSteps.setNoOfTravelers();
        bookingSteps.chooseTourOption(tourOption);
    }

    @Test
    public void tc004ForgotPasswordTest() {
        loginSteps.recoverPassword();
        logger.info("Expected URL to contain 'forgot-password'\nActual URL: " + driver.getCurrentUrl());
        Assert.assertTrue("User was not redirected to the 'Forgot Password' page",
                driver.getCurrentUrl().contains("forgot-password"));
    }
}
