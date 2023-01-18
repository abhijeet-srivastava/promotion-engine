import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.test.assignments.models.Cart;
import com.test.assignments.models.SKU;
import com.test.assignments.promotions.PromotionRuleSkuQuantity;
import com.test.assignments.promotions.PromotionalRuleSKUCombination;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PromotionEngineTest {
    SKU skua = new SKU("A", 50d);
    SKU skub = new SKU("B", 30d);
    SKU skuc = new SKU("C", 20d);
    SKU skud = new SKU("D", 15d);

    PromotionEngine pr = new PromotionEngine(ImmutableSet.of(
            new PromotionRuleSkuQuantity(
                    "Promotion on SKU A",
                    ImmutableMap.of(skua, 3),
                    130d
            ),
            new PromotionRuleSkuQuantity(
                    "Promotion on SKU B",
                    ImmutableMap.of(skub, 2),
                    50d
            ),
            new PromotionalRuleSKUCombination(
                    "Promotion on SKU C & D",
                    ImmutableMap.of(
                            skuc, 1,
                            skud, 1
                    ),
                    30d)
    ));


    @Test
    void testApplyPromotion1() {
        Cart cart = createCart1();
        Cart postPromotion = pr.applyPromotions(cart);
        assertEquals(300, cart.calculateCost());
        assertEquals(280, postPromotion.calculateCost());
    }

    @Test
    void testApplyPromotion2() {
        Cart cart = createCart2();
        Cart postPromotion = pr.applyPromotions(cart);
        assertEquals(320, cart.calculateCost());
        assertEquals(280, postPromotion.calculateCost());
    }

    @Test
    void testApplyPromotion3() {
        Cart cart = createCart3();
        Cart postPromotion = pr.applyPromotions(cart);
        assertEquals(420, cart.calculateCost());
        assertEquals(380, postPromotion.calculateCost());
    }

    @Test
    void testApplyPromotion4() {
        Cart cart = createCart4();
        Cart postPromotion = pr.applyPromotions(cart);
        assertEquals(335, cart.calculateCost());
        assertEquals(290, postPromotion.calculateCost());
    }

    private Cart createCart4() {
        Cart cart = new Cart();
        cart.addToCart(skua, 3);// 130 = 130
        cart.addToCart(skub, 5);//2*50 + 30 = 130
        cart.addToCart(skuc, 1);
        cart.addToCart(skud, 1);//30
        return cart;//Total = 290
    }

    private Cart createCart3() {
        Cart cart = new Cart();
        cart.addToCart(skua, 5);// 130 + 2*50 = 230
        cart.addToCart(skub, 5);//2*50 + 30 = 130
        cart.addToCart(skuc, 1);//20
        return cart;//Total = 380
    }

    private Cart createCart1() {
        Cart cart = new Cart();
        cart.addToCart(skua, 5);// 1*130 + 2*50 = 230
        cart.addToCart(skub, 1);//30
        cart.addToCart(skuc, 1);//20
        return cart;//Total = 280
    }
    private Cart createCart2() {
        Cart cart = new Cart();
        cart.addToCart(skua, 3); //130
        cart.addToCart(skub, 5);// 2*50 + 30 = 130
        cart.addToCart(skuc, 1);//20
        return cart;//280
    }
}