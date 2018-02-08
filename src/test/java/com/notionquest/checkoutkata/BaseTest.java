package com.notionquest.checkoutkata;

import com.notionquest.checkoutkata.service.CalculateItemPrice;
import com.notionquest.checkoutkata.service.CheckoutService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationTestConfig.class)
public class BaseTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    protected CheckoutService checkoutService;

    @Autowired
    protected CalculateItemPrice calculateItemPrice;

    @Test
    public void shouldGetCheckoutServiceBean() {
        Assert.assertNotNull(checkoutService);
    }

    @Test
    public void shouldGetCalculateItemPriceBean() {
        Assert.assertNotNull(calculateItemPrice);
    }
}