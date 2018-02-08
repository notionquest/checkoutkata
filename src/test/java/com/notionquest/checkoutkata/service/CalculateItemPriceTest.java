package com.notionquest.checkoutkata.service;

import com.notionquest.checkoutkata.BaseTest;
import com.notionquest.checkoutkata.exception.CheckoutKataApplicationException;
import com.notionquest.checkoutkata.model.ItemOfferPrice;
import com.notionquest.checkoutkata.model.ItemPrice;
import com.notionquest.checkoutkata.util.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class CalculateItemPriceTest extends BaseTest {

    private Set<ItemPrice> itemPrices;

    @Before
    public void init() {
        itemPrices = new HashSet<>();

        ItemPrice itemPriceA = new ItemPrice("A", 50.0, new ItemOfferPrice(3.0, 130.0));
        itemPrices.add(itemPriceA);

        ItemPrice itemPriceB = new ItemPrice("B", 30.0, new ItemOfferPrice(2.0, 45.0));
        itemPrices.add(itemPriceB);

        ItemPrice itemPriceC = new ItemPrice("C", 20.0);
        itemPrices.add(itemPriceC);

        ItemPrice itemPriceD = new ItemPrice("D", 15.0);
        itemPrices.add(itemPriceD);

        ItemPrice itemPricePotato = new ItemPrice("Potato", 15.0);
        itemPrices.add(itemPricePotato);

    }

    @Test
    public void shouldReturnItemPriceWithoutOffer() {
        Assert.assertEquals(new Double(50.0),calculateItemPrice.getItemPrice("A", 1.0, itemPrices));

    }

    @Test
    public void shouldReturnItemPriceWhenAllQuantitiesAreEligibleForOffer() {
        Assert.assertEquals(new Double(130.0),calculateItemPrice.getItemPrice("A", 3.0, itemPrices));

    }

    @Test
    public void shouldReturnItemPriceWhenSomeQuantitiesAreEligibleForOffer() {
        Assert.assertEquals(new Double(230.0),calculateItemPrice.getItemPrice("A", 5.0, itemPrices));

    }

    @Test
    public void shouldThrowExceptionWhenItemNotFound() {
        thrown.expect(CheckoutKataApplicationException.class);
        thrown.expectMessage(Constants.ITEM_NOT_FOUND_IN_PRICE_LIST);
        calculateItemPrice.getItemPrice("E", 5.0, itemPrices);
    }

    @Test
    public void shouldReturnPriceForItemWhichDoesnotHaveSpecialOffer() {
        Assert.assertEquals(new Double(40.0),calculateItemPrice.getItemPrice("C", 2.0, itemPrices));

    }

    @Test
    public void shouldReturnPriceInNegativeWhenItemIsReturned() {
        Assert.assertEquals(new Double(-20.0),calculateItemPrice.getItemPrice("C", -1.0, itemPrices));

    }

    @Test
    public void shouldReturnPriceWhenItemInLowerCase() {
        Assert.assertEquals(new Double(15.0),calculateItemPrice.getItemPrice("potato", 1.0, itemPrices));

    }
}
