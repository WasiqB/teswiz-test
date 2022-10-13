package com.znsio.sample.e2e.screen.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductItem {
    private final WebElement item;
    private final By         price = By.cssSelector ("span.a-price-whole");
    private final By         title = By.cssSelector ("h2 > a");

    public ProductItem (final WebElement item) {
        this.item = item;
    }

    public void click () {
        this.item.findElement (this.title)
            .click ();
    }

    public String getPrice () {
        return this.item.findElement (this.price)
            .getText ();
    }

    public String getTitle () {
        return this.item.findElement (this.title)
            .getText ();
    }
}
