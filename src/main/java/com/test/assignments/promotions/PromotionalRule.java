package com.test.assignments.promotions;

import lombok.Getter;

@Getter
public abstract class PromotionalRule implements  IPromotionalRule {
    private final String name;
    private final Integer priority;

    protected PromotionalRule(String name, Integer priority) {
        this.name = name;
        this.priority = priority;
    }
}
