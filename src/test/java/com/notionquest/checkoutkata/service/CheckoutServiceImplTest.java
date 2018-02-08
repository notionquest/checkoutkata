package com.notionquest.checkoutkata.service;


import com.notionquest.checkoutkata.BaseTest;
import com.notionquest.checkoutkata.exception.CheckoutKataApplicationException;
import com.notionquest.checkoutkata.model.ItemOfferPrice;
import com.notionquest.checkoutkata.model.ItemPrice;
import com.notionquest.checkoutkata.model.ShoppingItem;
import com.notionquest.checkoutkata.util.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CheckoutServiceImplTest extends BaseTest {

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
    public void shouldReturnExceptionWhenShoppingItemIsNull() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(Constants.SHOPPING_ITEM_EMPTY);
        checkoutService.calculateTotal(null, itemPrices);
    }

    @Test
    public void shouldReturnExceptionWhenShoppingItemsAreEmpty() {

        List<ShoppingItem> shoppingItemsEmptyList = new ArrayList<>();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(Constants.SHOPPING_ITEM_EMPTY);
        checkoutService.calculateTotal(shoppingItemsEmptyList, itemPrices);
    }

    @Test
    public void shouldReturnExceptionWhenItemPriceIsNull() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(Constants.ITEM_PRICE_EMPTY);

        ShoppingItem shoppingItemB = new ShoppingItem("B", 1.0);
        List<ShoppingItem> shoppingItems = Arrays.asList(shoppingItemB);
        checkoutService.calculateTotal(shoppingItems, null);
    }

    @Test
    public void shouldReturnExceptionWhenItemPricesAreEmpty() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(Constants.ITEM_PRICE_EMPTY);

        Set<ItemPrice> itemPricesEmptySet = new HashSet<>();

        ShoppingItem shoppingItemB = new ShoppingItem("B", 1.0);
        List<ShoppingItem> shoppingItems = Arrays.asList(shoppingItemB);
        checkoutService.calculateTotal(shoppingItems, itemPricesEmptySet);
    }

    @Test
    public void shouldReturnExceptionWhenShoppingItemsNotFoundInPriceList() {

        thrown.expect(CheckoutKataApplicationException.class);
        thrown.expectMessage(Constants.SHOPPING_ITEMS_NOT_PRESENT_IN_PRICE_LIST);

        List<ShoppingItem> shoppingItems = Arrays.asList(new ShoppingItem("E", 1.0));
        checkoutService.calculateTotal(shoppingItems, itemPrices);
    }

    @Test
    public void shouldReturnTotalAmountForOneItem() {

        List<ShoppingItem> shoppingItems = Arrays.asList(new ShoppingItem("B", 1.0));
        Assert.assertEquals(new Double(30.0), checkoutService.calculateTotal(shoppingItems, itemPrices));
    }

    @Test
    public void shouldReturnTotalAmountForMultipleItems() {

        List<ShoppingItem> shoppingItems = Arrays.asList(new ShoppingItem("b", 1.0), new ShoppingItem("A", 1.0), new ShoppingItem("B", 1.0));
        Assert.assertEquals(new Double(95.0), checkoutService.calculateTotal(shoppingItems, itemPrices));
    }

    //Item A
    @Test
    public void shouldReturnTotalAmountForItemAInSpecialOffers() {

        List<ShoppingItem> shoppingItems = Arrays.asList(new ShoppingItem("A", 1.0), new ShoppingItem("A", 1.0), new ShoppingItem("A", 1.0));
        Assert.assertEquals(new Double(130.0), checkoutService.calculateTotal(shoppingItems, itemPrices));
    }

    //Item B
    @Test
    public void shouldReturnTotalAmountForItemBInSpecialOffers() {

        List<ShoppingItem> shoppingItems = Arrays.asList(new ShoppingItem("B", 1.0), new ShoppingItem("B", 1.0));
        Assert.assertEquals(new Double(45.0), checkoutService.calculateTotal(shoppingItems, itemPrices));
    }


    @Test
    public void shouldReturnTotalAmountForItemsWhenNotInSpecialOffers() {

        List<ShoppingItem> shoppingItems = Arrays.asList(new ShoppingItem("C", 1.0), new ShoppingItem("D", 1.0), new ShoppingItem("C", 1.0));
        Assert.assertEquals(new Double(55.0), checkoutService.calculateTotal(shoppingItems, itemPrices));
    }

    @Test
    public void shouldReturnTotalAmountForItemsInWeights() {

        List<ShoppingItem> shoppingItems = Arrays.asList(new ShoppingItem("Potato", 0.5), new ShoppingItem("D", 1.0), new ShoppingItem("C", 1.0));
        Assert.assertEquals(new Double(42.5), checkoutService.calculateTotal(shoppingItems, itemPrices));
    }

    @Test
    public void shouldReturnTotalAmountForItemsInWeightsAndItemsInSpecialOffer() {

        List<ShoppingItem> shoppingItems = Arrays.asList(new ShoppingItem("Potato", 0.5), new ShoppingItem("A", 1.0), new ShoppingItem("C", 1.0), new ShoppingItem("A", 1.0), new ShoppingItem("A", 1.0));
        Assert.assertEquals(new Double(157.5), checkoutService.calculateTotal(shoppingItems, itemPrices));
    }

    @Test
    public void shouldReturnTotalAmountForItemsIncludingReturns() {

        List<ShoppingItem> shoppingItems = Arrays.asList(new ShoppingItem("A", 1.0), new ShoppingItem("A", 1.0), new ShoppingItem("A", -1.0));
        Assert.assertEquals(new Double(50.0), checkoutService.calculateTotal(shoppingItems, itemPrices));
    }

    @Test
    public void shouldReturnTotalAmountAsZeroWhenQuantityIsZero() {

        List<ShoppingItem> shoppingItems = Arrays.asList(new ShoppingItem("A", 0.0));
        Assert.assertEquals(new Double(0.0), checkoutService.calculateTotal(shoppingItems, itemPrices));
    }

    @Test
    public void shouldReturnExceptionWhenMaximumQuantitySelected() {

        thrown.expect(CheckoutKataApplicationException.class);
        thrown.expectMessage(Constants.ARITHMETIC_OVERFLOW);
        List<ShoppingItem> shoppingItems = Arrays.asList(new ShoppingItem("A", Double.MAX_VALUE), new ShoppingItem("A", Double.MAX_VALUE));
        checkoutService.calculateTotal(shoppingItems, itemPrices);
    }

}
