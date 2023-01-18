package com.test.assignments.promotions;

import com.test.assignments.models.Cart;
import com.test.assignments.models.PromotionalSKU;
import com.test.assignments.models.SKU;

import java.util.Map;

public class PromotionRuleSkuQuantity extends PromotionalRule {

    private final Map<SKU, Integer> requiredSkuQuantities;
    private final Double price;
    public PromotionRuleSkuQuantity(String name,
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
        for(Map.Entry<SKU, Integer> promotionSkuEntries : this.requiredSkuQuantities.entrySet()) {
            int reqQuantity = promotionSkuEntries.getValue();
            int existingQuantity = updatedCart.getQuantity(promotionSkuEntries.getKey());
            int promotionalSkuQuantity = existingQuantity/reqQuantity;
            int removedQuantity = promotionalSkuQuantity*reqQuantity;
            updatedCart.removeFromCart(promotionSkuEntries.getKey(), removedQuantity);
            PromotionalSKU ps = new PromotionalSKU(this, this.price);
            updatedCart.addToCart(ps,  promotionalSkuQuantity);
        }
        return updatedCart;
    }
}
