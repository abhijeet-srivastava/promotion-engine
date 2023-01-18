import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.test.assignments.models.Cart;
import com.test.assignments.models.SKU;
import com.test.assignments.promotions.PromotionRuleSkuQuantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PromotionEngineTest {
    SKU skua = new SKU("A", 50d);
    SKU skub = new SKU("B", 30d);
    SKU skuc = new SKU("C", 20d);
    SKU skud = new SKU("A", 15d);

    PromotionEngine pr = new PromotionEngine(ImmutableSet.of(
            new PromotionRuleSkuQuantity(
                    "Promotion on SKU A",
                    ImmutableMap.of(skua, 3),
                    130d
            )
    ));


    @Test
    void testApplyPromotion1() {
        Cart cart = createCart1();
        Cart postPromotion = pr.applyPromotions(cart);
        assertEquals(300, cart.calculateCost());
        assertEquals(280, postPromotion.calculateCost());
    }

    private Cart createCart1() {
        Cart cart = new Cart();
        cart.addToCart(skua, 5);
        cart.addToCart(skub, 1);
        cart.addToCart(skuc, 1);
        return cart;
    }
}