package com.viator.runners.tests;

import com.viator.app.steps.checkout.CheckoutSteps;
import com.viator.app.steps.checkout.PaymentSteps;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "src/test/resources/testdata/travelerinformationtestdata.csv")
//@Concurrent /*To be uncommented for test iterations parallel run*/
public class TC007TravelerInformationTest {

    @Managed
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup(); //takes care of chromedriver for local runs
    }

    @Steps
    public BookingSteps bookingSteps;

    @Steps
    public PaymentSteps paymentSteps;

    @Steps
    public CheckoutSteps checkoutSteps;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String expectedErrorMessage;
    private String windowTitle;
    private String baseUrl = ConfigUtils.getProperty("BASE_URL");
    private String tourOption = ConfigUtils.getProperty("TOUR_OPTION");

    @Qualifier /*used for distinguishing data sets*/
    public String qualifier() {
        return firstName + "=>" + lastName + "=>" + email + "=>" + phone + "=>" + expectedErrorMessage + "=>" + windowTitle;
    }

    @Before
    public void setUp() {
        driver.get(baseUrl);
        bookingSteps.setNoOfTravelers();
        bookingSteps.chooseTourOption(tourOption);
    }

    @Test
    public void tc007TravelerInformationTest() {
        checkoutSteps.fillTravelerInformationForm(firstName, lastName, email, phone);
        paymentSteps.selectPayPalPaymentMethod();
        checkoutSteps.payWithPayPal();
        if (expectedErrorMessage.isEmpty()) {
            Assert.assertEquals("There are still error messages", 0, checkoutSteps.getErrorMessages().size());
            Assert.assertEquals("PayPal window is not open", windowTitle, paymentSteps.getNewlyOpenedWindowTitle());
        } else {
            Assert.assertEquals("", 1, checkoutSteps.getErrorMessages().size());
            Assert.assertEquals("Incorrect error message", expectedErrorMessage, checkoutSteps.getErrorMessages().get(0));
        }
    }
}