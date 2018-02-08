package com.notionquest.checkoutkata.service;

import com.notionquest.checkoutkata.exception.CheckoutKataApplicationException;
import com.notionquest.checkoutkata.model.ItemPrice;
import com.notionquest.checkoutkata.util.Constants;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class CalculateItemPriceImpl implements CalculateItemPrice {
    @Override
    public Double getItemPrice(String stockKeepingUnit, Double quantity, Set<ItemPrice> itemPrices) {
        Double itemAmt = null;
        try {
            ItemPrice itemPrice = itemPrices.stream().filter(e -> e.getStockKeepingUnit().equalsIgnoreCase(stockKeepingUnit)).findFirst().get();
            if (itemPrice.getItemOfferPrice() != null && quantity >= itemPrice.getItemOfferPrice().getQuantity()) {
                Double itemsNotInOfferPrice = quantity % itemPrice.getItemOfferPrice().getQuantity();
                itemAmt = itemsNotInOfferPrice * itemPrice.getPrice();

                Double itemsInOfferPrice =  Math.floor(quantity/itemPrice.getItemOfferPrice().getQuantity());

                itemAmt =itemAmt + (itemsInOfferPrice * itemPrice.getItemOfferPrice().getOfferPrice());

            } else {
                itemAmt = quantity * itemPrice.getPrice();
            }
        } catch (NoSuchElementException ne) {
            throw new CheckoutKataApplicationException(Constants.ITEM_NOT_FOUND_IN_PRICE_LIST, ne);
        }

        return itemAmt;
    }
}
