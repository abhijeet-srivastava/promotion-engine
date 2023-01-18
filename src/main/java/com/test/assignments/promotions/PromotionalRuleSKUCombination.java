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
        if(!isApplicable(cart)) {
            throw new RuntimeException(String.format("Promotion %s is not applicable on Cart", this.getName()));
        }
        Cart updatedCart = new Cart(cart);
        int minPromotionQuantity = Integer.MAX_VALUE;
        for(Map.Entry<SKU, Integer> promotionSkuEntries : this.requiredSkuQuantities.entrySet()) {
            int reqQuantity = promotionSkuEntries.getValue();
            int existingQuantity = updatedCart.getQuantity(promotionSkuEntries.getKey());
            int promotionalSkuQuantity = existingQuantity/reqQuantity;
            minPromotionQuantity = Math.min(minPromotionQuantity, promotionalSkuQuantity);
        }
        updatedCart.addToCart(new PromotionalSKU(this, this.price), minPromotionQuantity);
        for(Map.Entry<SKU, Integer> promotionSkuEntries : this.requiredSkuQuantities.entrySet()) {
            int removedQuantity = minPromotionQuantity*promotionSkuEntries.getValue();
            updatedCart.removeFromCart(promotionSkuEntries.getKey(), removedQuantity);
        }
        return updatedCart;
    }
}
