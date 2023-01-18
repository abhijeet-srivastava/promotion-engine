import com.test.assignments.models.Cart;
import com.test.assignments.promotions.PromotionalRule;

import java.util.HashSet;
import java.util.Set;

public class PromotionEngine {
    private final Set<PromotionalRule> promotionalRules;

    public PromotionEngine(Set<PromotionalRule> promotionalRules) {
        this.promotionalRules = new HashSet<>(promotionalRules);
    }

    public Cart applyPromotions(Cart cart) {
        for(PromotionalRule pr: promotionalRules) {
            if(pr.isApplicable(cart)) {
                cart = pr.apply(cart);
            }
        }
        return cart;
    }
}
