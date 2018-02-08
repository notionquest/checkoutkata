package com.notionquest.checkoutkata.model;

import java.io.Serializable;

/**
 * Normal item price for stock keeping unit
 */
public class ItemPrice implements Serializable {
    private static final long serialVersionUID = -5602986575931767611L;

    private String stockKeepingUnit;
    private Double price;
    private ItemOfferPrice itemOfferPrice;

    public ItemPrice(String stockKeepingUnit, Double price) {
        this.stockKeepingUnit = stockKeepingUnit;
        this.price = price;
    }

    public ItemPrice(String stockKeepingUnit, Double price, ItemOfferPrice itemOfferPrice) {
        this.stockKeepingUnit = stockKeepingUnit;
        this.price = price;
        this.itemOfferPrice = itemOfferPrice;
    }

    public String getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setStockKeepingUnit(String stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ItemOfferPrice getItemOfferPrice() {
        return itemOfferPrice;
    }

    public void setItemOfferPrice(ItemOfferPrice itemOfferPrice) {
        this.itemOfferPrice = itemOfferPrice;
    }

    @Override
    public String toString() {
        return "ItemPrice{" +
                "stockKeepingUnit='" + stockKeepingUnit + '\'' +
                ", price=" + price +
                ", itemOfferPrice=" + itemOfferPrice +
                '}';
    }

}
