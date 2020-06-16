package com.viator.runners.tests;

import com.viator.app.steps.BookingSteps;
import com.viator.app.steps.checkout.CheckoutSteps;
import com.viator.app.steps.checkout.PaymentSteps;
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
@UseTestDataFrom(value = "src/test/resources/testdata/cardpaymenttestdata.csv")
/*The second run (the one taking data from row 3 in the .csv) will fail because it doesn't have real credit card
information and the payment fails.
Once given the details of a card that can actually pay, it will pass.
 */
public class TC010CardPaymentTest {

    @Managed
    private WebDriver driver;

    @Steps
    public CheckoutSteps checkoutSteps;

    @Steps
    public PaymentSteps paymentSteps;

    @Steps
    public BookingSteps bookingSteps;

    private String baseUrl = ConfigUtils.getProperty("BASE_URL");
    private String firstName = ConfigUtils.getProperty("TRAVELER_FIRST_NAME");
    private String lastName = ConfigUtils.getProperty("TRAVELER_LAST_NAME");
    private String email = ConfigUtils.getProperty("TRAVELER_EMAIL");
    private String phoneNumber = ConfigUtils.getProperty("TRAVELER_PHONE_NUMBER");
    private String tourOption = ConfigUtils.getProperty("TOUR_OPTION");
    private String cardholder;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String securityCode;
    private String expectedErrorMessage;

    @Qualifier /*used for distinguishing data sets*/
    public String qualifier() {
        return cardholder + "=>" + cardNumber + "=>" + expirationMonth + "=>" + expirationYear + "=>" + securityCode
                + "=>" + expectedErrorMessage;
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
    public void tc010CardPaymentTest() {
        checkoutSteps.fillTravelerInformationForm(firstName, lastName, email, phoneNumber);
        paymentSteps.fillCardInformationForm(cardholder, cardNumber, expirationMonth, expirationYear, securityCode);
        checkoutSteps.payWithCreditCard();
        Assert.assertEquals("Incorrect error message", expectedErrorMessage,
                paymentSteps.getPaymentErrorText());
    }
}
