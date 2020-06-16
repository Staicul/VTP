package com.viator.runners.tests;

import com.viator.app.steps.LoginSteps;
import com.viator.app.steps.BookingSteps;
import com.viator.tools.ConfigUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "src/test/resources/testdata/logintestdata.csv")
public class TC002LoginTest {

    @Managed
    private WebDriver driver;

    @Steps
    public LoginSteps loginSteps;

    @Steps
    public BookingSteps bookingSteps;

    private String baseUrl = ConfigUtils.getProperty("BASE_URL");
    private String tourOption = ConfigUtils.getProperty("TOUR_OPTION");
    private String email;
    private String password;
    private String expectedSuccessMessage;
    private String expectedErrorMessage;

    @Qualifier /*used for distinguishing data sets*/
    public String qualifier() {
        return email + "=>" + password + "=>" + expectedErrorMessage;
    }

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup(); //takes care of chromedriver for local runs
    }

    @Before
    public void setUp() {
        driver.get(baseUrl);
        bookingSteps.setNoOfTravelers();
        bookingSteps.chooseTourOption(tourOption);
    }

    @Test
    public void tc002LoginTest() {
        loginSteps.logInWithCredentials(email, password);
        if (expectedErrorMessage.isEmpty()) {
            expectedSuccessMessage = expectedSuccessMessage.concat("\n");
            Assert.assertEquals("Login failed.", expectedSuccessMessage + email,
                    loginSteps.getLoginSuccessMessage());
        } else {
            Assert.assertEquals("No error message", expectedErrorMessage, loginSteps.getLoginErrorMessage());
        }
    }
}
