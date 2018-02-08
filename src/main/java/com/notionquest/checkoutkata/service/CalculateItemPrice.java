package com.notionquest.checkoutkata.service;

import com.notionquest.checkoutkata.model.ItemPrice;

import java.util.Set;

public interface CalculateItemPrice {
    /**
     *
     * Calculates the price for a each item
     *
     * @param stockKeepingUnit
     * @param quantity
     * @param itemPrices
     * @return
     */
    Double getItemPrice(String stockKeepingUnit, Double quantity, Set<ItemPrice> itemPrices);
}
