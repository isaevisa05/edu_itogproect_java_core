package edu.itogproect.java.core.entity.npc;

import edu.itogproect.java.core.entity.Seller;

import java.io.OutputStream;
import java.io.PrintStream;

public class Merchant implements Seller {


    @Override
    public void sell(SellItem item) {

    }

    public void getSellItem (PrintStream printStream) { // Изначально все торговцы продают всё!
        printStream.println("Вещи на продажу");
        for(SellItem item : SellItem.values()) {
            printStream.println(
                    new StringBuilder()
                            .append("товар №")
                            .append(item.getId())
                            .append("")
                            .append(item.toString().toLowerCase())
                            .append(" ")
                            .append(item.name)
                            .toString()
            );
        }
        printStream.println("Что бы купить что-то введи название товара и кол-во товара которое хотите купить");
    }

    public enum SellItem {
        GOLDEN_APPLE(1,"Золотое яблоко"),
        ENCHANTED_GOLDEN_APPLE(2, "Зачарованное золотое яблоко"),
        HEALTH_POINT(3, "Зелье восстанавливающее ХП");

        private int id;
        private String name;

        private SellItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
