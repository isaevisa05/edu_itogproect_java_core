package edu.itogproect.java.core.entity.trade;

import java.io.PrintStream;

public class Merchant {

    public static void getSellItem (PrintStream printStream) { // Изначально все торговцы продают всё!
        printStream.println("Вещи на продажу");
        for(SellItem item : SellItem.values()) {
            printStream.println(
                    new StringBuilder()
                            .append("товар ->  ")
                            .append(item.toString().toLowerCase())
                            .append(" ")
                            .append(item.name)
                            .append(" цена: ")
                            .append(item.getPrice())
                            .append(" gold")
            );
        }
        printStream.println("Что бы купить что-то введи название товара на английском языке и кол-во товара которое хотите купить");
        printStream.println("например: golden_apple 7");
        printStream.println("таким образом вы купите 7 золотых яблок и сразу же их используете");
    }

    public enum SellItem {
        GOLDEN_APPLE(1,"Золотое яблоко", 50),
        ENCHANTED_GOLDEN_APPLE(2, "Зачарованное золотое яблоко", 100),
        HEALTH_POINT(3, "Зелье восстанавливающее ХП", 150);

        private final int id;
        private final String name;
        private final int price;

        private SellItem(int id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }
    }
}
