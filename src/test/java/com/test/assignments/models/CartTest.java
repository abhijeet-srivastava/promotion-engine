package com.test.assignments.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    SKU skua = new SKU("A", 50d);
    SKU skub = new SKU("B", 30d);
    SKU skuc = new SKU("C", 20d);
    SKU skud = new SKU("A", 15d);


    @Test
    void addToCart() {
        Cart cart = new Cart();
        cart.addToCart(skua, 5);
        cart.addToCart(skua, 3);
        assertEquals(8, cart.getQuantity(skua));
    }

    @Test
    void removeFromCart() {
        Cart cart = new Cart();
        cart.addToCart(skua, 2);
        cart.addToCart(skua, 3);
        cart.addToCart(skub, 5);
        cart.removeFromCart(skua);
        assertEquals(0, cart.getQuantity(skua));
    }

    @Test
    void testRemoveFromCart() {
        Cart cart = new Cart();
        cart.addToCart(skua, 5);
        cart.addToCart(skua, 3);
        cart.addToCart(skub, 5);
        cart.removeFromCart(skua, 4);
        assertEquals(4, cart.getQuantity(skua));
    }

    @Test
    void calculateCost() {
        Cart cart = new Cart();
        cart.addToCart(skua, 1);
        cart.addToCart(skub, 1);
        cart.addToCart(skuc, 1);
        assertEquals(100, cart.getQuantity(skua));
    }
}