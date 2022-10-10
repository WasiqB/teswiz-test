package com.znsio.sample.e2e.screen.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.sample.e2e.screen.CartScreen;
import com.znsio.sample.e2e.screen.ProductDetailScreen;
import org.openqa.selenium.By;

public class ProductDetailScreenWeb extends ProductDetailScreen {
    private final By     addToCartButton = By.id ("add-to-cart-button");
    private final By     cartButton      = By.cssSelector ("form#attach-view-cart-button-form input");
    private final Driver driver;
    private final By     price           = By.cssSelector (
        "div#corePriceDisplay_desktop_feature_div span.a-price-whole");
    private final By     title           = By.id ("productTitle");

    public ProductDetailScreenWeb (final Driver driver) {
        this.driver = driver;
    }

    @Override
    public CartScreen addToCart () {
        this.driver.findElement (this.addToCartButton)
            .click ();
        this.driver.findElement (this.cartButton)
            .click ();
        return CartScreen.get ();
    }

    @Override
    public String price () {
        return this.driver.findElement (this.price)
            .getText ();
    }

    @Override
    public String title () {
        return this.driver.findElement (this.title)
            .getText ();
    }
}
