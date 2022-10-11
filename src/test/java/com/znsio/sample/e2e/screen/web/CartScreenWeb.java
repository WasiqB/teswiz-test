package com.znsio.sample.e2e.screen.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.sample.e2e.screen.CartScreen;
import org.openqa.selenium.By;

public class CartScreenWeb extends CartScreen {
    private final Driver driver;
    private final By     productPrice = By.cssSelector (
        "form#activeCartViewForm div.sc-list-item-content span.sc-product-price");
    private final By     productTitle = By.cssSelector (
        "form#activeCartViewForm div.sc-list-item-content span.sc-product-title");

    public CartScreenWeb (final Driver driver) {
        this.driver = driver;
    }

    @Override
    public String price () {
        return this.driver.findElement (this.productPrice)
            .getText ();
    }

    @Override
    public String title () {
        return this.driver.findElement (this.productTitle)
            .getText ();
    }
}
