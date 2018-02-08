package com.notionquest.checkoutkata.service;

import com.notionquest.checkoutkata.model.ItemPrice;
import com.notionquest.checkoutkata.model.ShoppingItem;

import java.util.List;
import java.util.Set;

/**
 * Shopping cart check out service
 */
public interface CheckoutService {

    /**
     * Calculate the total amount for the shopping items
     *
     * @param shoppingItems
     * @param itemPrices
     * @return
     */
    Double calculateTotal(List<ShoppingItem> shoppingItems, Set<ItemPrice> itemPrices);
}
