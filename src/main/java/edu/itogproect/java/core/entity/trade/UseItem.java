package edu.itogproect.java.core.entity.trade;

import edu.itogproect.java.core.entity.Entity;

public class UseItem {

    // Здесь при использовании предметов даётся что то
    //

    public static void use(Entity entity, Merchant.SellItem item, int amount) {
        switch (item) {
            case GOLDEN_APPLE:
                entity.setHealth(entity.getHealth() + (5 * amount));
                return;
            case ENCHANTED_GOLDEN_APPLE:
                entity.setHealth(entity.getHealth() + (10 * amount));
                return;
            case HEALTH_POINT:
                entity.setHealth(entity.getHealth() + (15 * amount));
                return;
            default:
                return;
        }
    }

}
