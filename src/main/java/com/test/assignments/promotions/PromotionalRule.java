package com.test.assignments.promotions;

import lombok.Getter;

@Getter
public abstract class PromotionalRule implements  IPromotionalRule {
    private final String name;

    protected PromotionalRule(String name) {
        this.name = name;
    }
}
