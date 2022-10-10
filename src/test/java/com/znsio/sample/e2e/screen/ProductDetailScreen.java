package com.znsio.sample.e2e.screen;

import static com.znsio.e2e.runner.Runner.fetchDriver;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.sample.e2e.screen.web.ProductDetailScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class ProductDetailScreen {
    private static final String SCREEN_NAME = ProductDetailScreen.class.getSimpleName ();
    private static final Logger LOGGER      = Logger.getLogger (SCREEN_NAME);

    public static ProductDetailScreen get () {
        final Driver driver = fetchDriver (Thread.currentThread ()
            .getId ());
        final Platform platform = Runner.fetchPlatform (Thread.currentThread ()
            .getId ());
        LOGGER.info (SCREEN_NAME + ": Driver type: " + driver.getType () + ": Platform: " + platform);

        if (platform == Platform.web) {
            return new ProductDetailScreenWeb (driver);
        }
        throw new NotImplementedException (SCREEN_NAME + " is not implemented in " + platform);
    }

    public abstract CartScreen addToCart ();

    public abstract String price ();

    public abstract String title ();
}
