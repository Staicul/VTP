package com.viator.runners.suites;

import com.viator.runners.tests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TC002LoginTest.class, TC004ForgotPasswordTest.class, TC007TravelerInformationTest.class,
        TC010CardPaymentTest.class, TC012PromoCodeTest.class})
public class ViatorSuite {
}
