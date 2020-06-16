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

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "src/test/resources/testdata/googlepaytestdata.csv")
//@Concurrent
public class TC008GooglePayTest {

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

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String windowTitle;
    private String baseUrl = ConfigUtils.getProperty("BASE_URL");
    private String tourOption = ConfigUtils.getProperty("TOUR_OPTION");

    @Qualifier /*used for distinguishing data sets*/
    public String qualifier() {
        return firstName + "=>" + lastName + "=>" + email + "=>" + phone + "=>" + windowTitle;
    }

    @Before
    public void setUp() {
        driver.get(baseUrl);
        bookingSteps.setNoOfTravelers();
        bookingSteps.chooseTourOption(tourOption);
    }

    @Test
    public void tc008GooglePayTest() {
        checkoutSteps.fillTravelerInformationForm(firstName, lastName, email, phone);
        paymentSteps.selectGPayPaymentMethod();
        checkoutSteps.payWithGPay();
        Assert.assertEquals("GPay window is not open", windowTitle, paymentSteps.getNewlyOpenedWindowTitle());
    }
}