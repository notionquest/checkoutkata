package com.notionquest.checkoutkata.service;

import com.notionquest.checkoutkata.exception.CheckoutKataApplicationException;
import com.notionquest.checkoutkata.model.ItemPrice;
import com.notionquest.checkoutkata.model.ShoppingItem;
import com.notionquest.checkoutkata.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service("checkoutService")
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private CalculateItemPrice calculateItemPrice;

    @Override
    public Double calculateTotal(List<ShoppingItem> shoppingItems, Set<ItemPrice> itemPrices) {

        Assert.notEmpty(shoppingItems, Constants.SHOPPING_ITEM_EMPTY);
        Assert.notEmpty(itemPrices, Constants.ITEM_PRICE_EMPTY);
        Double totalAmt = 0.0;
        if (validateShoppingItems(shoppingItems, itemPrices)) {

            Map<String, Double> shoppingItemsSum = shoppingItems.stream().collect(
                    Collectors.groupingBy(ShoppingItem::getStockKeepingUnit,
                            Collectors.summingDouble(ShoppingItem::getQuantity)));

            for (Map.Entry<String, Double> shoppingItemEntry : shoppingItemsSum.entrySet()) {
                totalAmt = totalAmt + calculateItemPrice.getItemPrice(shoppingItemEntry.getKey(), shoppingItemEntry.getValue(), itemPrices);
                if (totalAmt.isNaN() || totalAmt.isInfinite()) {
                    throw new CheckoutKataApplicationException(Constants.ARITHMETIC_OVERFLOW);
                }

            }


        } else {
            throw new CheckoutKataApplicationException(Constants.SHOPPING_ITEMS_NOT_PRESENT_IN_PRICE_LIST);
        }
        return totalAmt;
    }

    private boolean validateShoppingItems(List<ShoppingItem> shoppingItems, Set<ItemPrice> itemPrices) {
        Set<String> stockKeepingUnitInItemPrices = itemPrices.stream().map(e -> e.getStockKeepingUnit()).collect(Collectors.toSet());

        boolean allShoppingItemsFound = shoppingItems.stream().allMatch(e -> stockKeepingUnitInItemPrices.contains(e.getStockKeepingUnit()));

        return allShoppingItemsFound;
    }
}
