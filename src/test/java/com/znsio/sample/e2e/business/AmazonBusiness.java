package com.znsio.sample.e2e.business;

import static com.znsio.e2e.runner.Runner.getSoftAssertion;
import static com.znsio.e2e.runner.Runner.getTestExecutionContext;
import static com.znsio.e2e.runner.Runner.setCurrentDriverForUser;
import static java.lang.Thread.currentThread;
import static org.assertj.core.api.Assertions.assertThat;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.sample.e2e.screen.AmazonScreen;
import com.znsio.sample.e2e.screen.ProductDetailScreen;
import com.znsio.sample.e2e.screen.component.ProductItem;
import org.assertj.core.api.SoftAssertions;

public class AmazonBusiness {
    private       AmazonScreen         amazon;
    private final TestExecutionContext context;
    private       ProductDetailScreen  productDetail;
    private final SoftAssertions       softly;

    public AmazonBusiness () {
        final long threadId = currentThread ().getId ();
        this.context = getTestExecutionContext (threadId);
        this.softly = getSoftAssertion (threadId);
        final String currentUserPersona = "me";
        final Platform currentPlatform = Platform.web;
        setCurrentDriverForUser (currentUserPersona, currentPlatform, this.context);
    }

    public AmazonBusiness searchFor (final String productName) {
        this.amazon = AmazonScreen.get ();
        final var product = this.amazon.searchFor (productName, 0);
        this.context.addTestState ("product", product);
        return this;
    }

    public void verifyAddToCart () {
        final var cartScreen = this.productDetail.addToCart ();
        final var expectedTitle = this.context.getTestState ("productTitle")
            .toString ();
        final var expectedPrice = this.context.getTestState ("productPrice")
            .toString ();
        assertThat (cartScreen.title ()).as ("Product Title")
            .isEqualTo (expectedTitle);
        assertThat (cartScreen.price ()).as ("Product Price")
            .isEqualTo (expectedPrice);
    }

    public void verifySearchedProduct () {
        final var product = (ProductItem) this.context.getTestState ("product");
        this.context.addTestState ("productTitle", product.getTitle ());
        this.context.addTestState ("productPrice", product.getPrice ());
        final var title = product.getTitle ();
        final var price = product.getPrice ();

        this.productDetail = this.amazon.openProduct (product);
        assertThat (this.productDetail.title ()).as ("Product title")
            .isEqualTo (title);
        assertThat (this.productDetail.price ()).as ("Product price")
            .isEqualTo (price);
    }
}
