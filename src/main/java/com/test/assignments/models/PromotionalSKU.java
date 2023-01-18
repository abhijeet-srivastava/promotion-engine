package com.test.assignments.models;

import com.test.assignments.promotions.PromotionalRule;

public class PromotionalSKU extends SKU {
    private final PromotionalRule appliedPromotion;
    public PromotionalSKU(PromotionalRule appliedPromotion, Double price) {
        super(appliedPromotion.getName(), price);
        this.appliedPromotion = appliedPromotion;
    }
}
