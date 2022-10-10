package com.znsio.sample.e2e.screen;

import static com.znsio.e2e.runner.Runner.fetchDriver;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.sample.e2e.screen.component.ProductItem;
import com.znsio.sample.e2e.screen.web.AmazonScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class AmazonScreen {
    private static final String SCREEN_NAME = AmazonScreen.class.getSimpleName ();
    private static final Logger LOGGER      = Logger.getLogger (SCREEN_NAME);

    public static AmazonScreen get () {
        final Driver driver = fetchDriver (Thread.currentThread ()
            .getId ());
        final Platform platform = Runner.fetchPlatform (Thread.currentThread ()
            .getId ());
        LOGGER.info (SCREEN_NAME + ": Driver type: " + driver.getType () + ": Platform: " + platform);

        if (platform == Platform.web) {
            return new AmazonScreenWeb (driver);
        }
        throw new NotImplementedException (SCREEN_NAME + " is not implemented in " + platform);
    }

    public abstract ProductDetailScreen openProduct (ProductItem productItem);

    public abstract ProductItem searchFor (String productName, int index);
}
