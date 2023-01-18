package com.test.assignments.promotions;

import com.test.assignments.models.Cart;

public interface IPromotionalRule {

    boolean isApplicable(Cart cart);

    Cart apply(Cart cart);
}
