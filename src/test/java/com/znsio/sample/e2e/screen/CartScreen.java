package com.znsio.sample.e2e.screen;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static java.lang.Thread.currentThread;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.sample.e2e.screen.web.CartScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class CartScreen {
    private static final String SCREEN_NAME = CartScreen.class.getSimpleName ();
    private static final Logger LOGGER      = Logger.getLogger (SCREEN_NAME);

    public static CartScreen get () {
        final Driver driver = fetchDriver (currentThread ().getId ());
        final Platform platform = Runner.fetchPlatform (currentThread ().getId ());
        LOGGER.info (SCREEN_NAME + ": Driver type: " + driver.getType () + ": Platform: " + platform);

        if (platform == Platform.web) {
            return new CartScreenWeb (driver);
        }
        throw new NotImplementedException (SCREEN_NAME + " is not implemented in " + platform);
    }
}
