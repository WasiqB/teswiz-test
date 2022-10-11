package com.znsio.sample.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.entities.TEST_CONTEXT;
import com.znsio.e2e.steps.Hooks;
import com.znsio.e2e.tools.Drivers;
import com.znsio.sample.e2e.business.AmazonBusiness;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class AmazonSteps {
    private static final Logger LOGGER = Logger.getLogger (AmazonSteps.class.getName ());

    private TestExecutionContext context;

    @After
    public void afterScenario (final Scenario scenario) {
        new Hooks ().afterScenario (scenario);
    }

    @Before
    public void beforeScenario (final Scenario scenario) {
        new Hooks ().beforeScenario (scenario);
        this.context = SessionContext.getTestExecutionContext (Thread.currentThread ()
            .getId ());
        LOGGER.info ("context: " + this.context.getTestName ());
        final var allDrivers = (Drivers) this.context.getTestState (TEST_CONTEXT.ALL_DRIVERS);
        allDrivers.createDriverFor ("me", Platform.web, this.context);
    }

    @When ("I add the first product to cart")
    public void iAddTheFirstProductToCart () {
        final AmazonBusiness amazon = (AmazonBusiness) this.context.getTestState ("amazon");
        amazon.verifySearchedProduct ();
    }

    @Given ("I search for {string} on Amazon")
    public void iSearchForOnAmazon (final String productName) {
        final var amazon = new AmazonBusiness ();
        amazon.searchFor (productName);
        this.context.addTestState ("amazon", amazon);
    }

    @Then ("the same product should be available in the cart")
    public void theSameProductShouldBeAvailableInTheCart () {
        final AmazonBusiness amazon = (AmazonBusiness) this.context.getTestState ("amazon");
        amazon.verifyAddToCart ();
    }
}
