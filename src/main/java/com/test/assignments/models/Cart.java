package com.test.assignments.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<SKU, Integer> skuQuantities;

    public Cart() {
        this.skuQuantities = new HashMap<>();
    }

    public Cart(Cart cart) {
        this.skuQuantities = new HashMap<>(cart.skuQuantities);
    }

    /**
     * Return quantity of specific SKU,
     * If present is Cart, else 0
     * @param sku
     * @return
     */
    public int getQuantity(SKU sku) {
        return this.skuQuantities.getOrDefault(sku, 0);
    }
    /**
     * Add given quantities of  sku to Cart,
     * @param sku - SKU to add
     * @param quantity - quantity to add
     */
    public void addToCart(SKU sku, int quantity) {
        int existingQuantity = this.skuQuantities.getOrDefault(sku, 0);
        this.skuQuantities.put(sku, existingQuantity+quantity);
    }

    /**
     * Remove specific sku from Cart
     * @param sku - SKU to remove
     */
    public void removeFromCart(SKU sku) {
        this.skuQuantities.remove(sku);
    }

    /**
     * Remove specific quantities of an SKU from Cart
     * @param sku
     * @param quantity
     */
    public void removeFromCart(SKU sku, Integer quantity) {
        int existingQuantity = this.skuQuantities.getOrDefault(sku, 0);
        if(existingQuantity < quantity) {
            return;
        } else if(existingQuantity == quantity) {
            this.skuQuantities.remove(sku);
        } else {
            this.skuQuantities.put(sku, existingQuantity - quantity);
        }
    }

    public Double calculateCost() {
        double cost = 0.0d;
        for (Map.Entry<SKU, Integer> skuEntry: this.skuQuantities.entrySet()) {
            cost += skuEntry.getKey().getPrice()*skuEntry.getValue();
        }
        return cost;
    }
}
