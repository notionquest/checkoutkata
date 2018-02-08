package com.notionquest.checkoutkata.model;

import java.io.Serializable;

/**
 * Selected shopping items
 */
public class ShoppingItem implements Serializable {

    private static final long serialVersionUID = 347800599738820448L;

    private String stockKeepingUnit;
    private Double quantity;

    public ShoppingItem(String stockKeepingUnit, Double quantity) {
        this.stockKeepingUnit = stockKeepingUnit;
        this.quantity = quantity;
    }

    public String getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setStockKeepingUnit(String stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "stockKeepingUnit='" + stockKeepingUnit + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
