package com.test.assignments.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SKU {

    @EqualsAndHashCode.Include
    private final String name;
    private final Double price;

    public SKU(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
