package com.notionquest.checkoutkata.model;

import java.io.Serializable;

/**
 * Special offer item price for stock keeping unit
 */
public class ItemOfferPrice implements Serializable {
    private static final long serialVersionUID = 2548566001134068267L;

    private Double quantity;
    private Double offerPrice;

    public ItemOfferPrice(Double quantity, Double offerPrice) {
        this.quantity = quantity;
        this.offerPrice = offerPrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Double offerPrice) {
        this.offerPrice = offerPrice;
    }

    @Override
    public String toString() {
        return "ItemOfferPrice{" +
                "quantity=" + quantity +
                ", offerPrice=" + offerPrice +
                '}';
    }
}
