package edu.itogproect.java.core.entity;

import edu.itogproect.java.core.entity.npc.Merchant;

public class UseItem {

    // Здесь при использовании предметов даётся что то
    //

    public static void use(Entity entity, Merchant.SellItem item) {
        switch (item) {
            case GOLDEN_APPLE:
                entity.setHealth(entity.getHealth() + 5);
                return;
            case ENCHANTED_GOLDEN_APPLE:
                entity.setHealth(entity.getHealth() + 10);
                return;
            case HEALTH_POINT:
                entity.setHealth(entity.getHealth() + 15);
                return;
            default:
                return;
        }
    }

}
