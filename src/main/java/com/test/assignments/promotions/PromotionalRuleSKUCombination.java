package com.test.assignments.promotions;

import com.test.assignments.models.Cart;
import com.test.assignments.models.PromotionalSKU;
import com.test.assignments.models.SKU;

import java.util.Map;

public class PromotionalRuleSKUCombination extends PromotionalRule {

    private final Map<SKU, Integer> requiredSkuQuantities;
    private final Double price;

    public PromotionalRuleSKUCombination(String name,
                                         Map<SKU, Integer> requiredSkuQuantities,
                                         Double price) {
        super(name);
        this.requiredSkuQuantities = requiredSkuQuantities;
        this.price = price;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        boolean isApplicable = true;
        for (Map.Entry<SKU, Integer> promotionSkuEntry: this.requiredSkuQuantities.entrySet()) {
            if(cart.getQuantity(promotionSkuEntry.getKey()) < promotionSkuEntry.getValue()) {
                isApplicable = false;
                break;
            }
        }
        return isApplicable;
    }

    @Override
    public Cart apply(Cart cart) {
        return cart;
    }
}
