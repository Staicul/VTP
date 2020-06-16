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
@UseTestDataFrom(value = "src/test/resources/testdata/promocodetestdata.csv")
/*The second run (the one taking data from row 3 in the .csv) will fail because it doesn't have real promo code
information.
Once given a valid promo code, it will pass.
 */
public class TC012PromoCodeTest {

    @Managed
    private WebDriver driver;

    @Steps
    public CheckoutSteps checkoutSteps;

    @Steps
    public PaymentSteps paymentSteps;

    @Steps
    public BookingSteps bookingSteps;

    private String baseUrl = ConfigUtils.getProperty("BASE_URL");
    private String tourOption = ConfigUtils.getProperty("TOUR_OPTION");
    private String promoCode;
    private String expectedErrorMessage;

    @Qualifier /*used for distinguishing data sets*/
    public String qualifier() {
        return promoCode + "=>" + expectedErrorMessage;
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
    public void tc014PromoCodeTest() {
        checkoutSteps.addPromoCode(promoCode);
        Assert.assertEquals("Incorrect error message", expectedErrorMessage,
                checkoutSteps.getPromoCodeErrorMessage());
    }
}
