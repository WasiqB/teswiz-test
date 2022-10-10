package com.znsio.sample.e2e.screen.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.sample.e2e.screen.CartScreen;
import org.openqa.selenium.By;

public class CartScreenWeb extends CartScreen {
    private final Driver driver;
    private final By     productPrice = By.cssSelector (
        "div.sc-list-item-content div.sc-item-content-group:last-child ul div.sc-item-price-block");
    private final By     productTitle = By.cssSelector (
        "div.sc-list-item-content div.sc-item-content-group:last-child ul span.a-truncate-full");

    public CartScreenWeb (final Driver driver) {
        this.driver = driver;
    }
}
