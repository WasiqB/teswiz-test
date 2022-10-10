package com.znsio.sample.e2e.screen.web;

import static java.util.stream.Collectors.toList;

import com.znsio.e2e.tools.Driver;
import com.znsio.sample.e2e.screen.AmazonScreen;
import com.znsio.sample.e2e.screen.ProductDetailScreen;
import com.znsio.sample.e2e.screen.component.ProductItem;
import org.openqa.selenium.By;

public class AmazonScreenWeb extends AmazonScreen {
    private final Driver driver;

    private final By resultRow    = By.cssSelector ("div[data-component-type=\"s-search-result\"]");
    private final By searchBox    = By.id ("twotabsearchtextbox");
    private final By searchButton = By.id ("nav-search-submit-button");

    public AmazonScreenWeb (final Driver driver) {
        this.driver = driver;
    }

    @Override
    public ProductDetailScreen openProduct (final ProductItem productItem) {
        productItem.click ();
        this.driver.switchToNextTab ();
        return ProductDetailScreen.get ();
    }

    @Override
    public ProductItem searchFor (final String productName, final int index) {
        final var searchBoxElement = this.driver.waitForClickabilityOf (this.searchBox);
        searchBoxElement.sendKeys (productName);
        final var searchButtonElement = this.driver.waitForClickabilityOf (this.searchButton);
        searchButtonElement.click ();
        final var resultRows = this.driver.findElements (this.resultRow);
        return resultRows.stream ()
            .map (ProductItem::new)
            .filter (e -> e.getTitle ()
                .equals (productName))
            .collect (toList ())
            .get (index);
    }
}
