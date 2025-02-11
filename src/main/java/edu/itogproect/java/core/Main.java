package edu.itogproect.java.core;

import edu.itogproect.java.core.entity.Entity;
import edu.itogproect.java.core.entity.trade.UseItem;
import edu.itogproect.java.core.entity.monsters.Goblin;
import edu.itogproect.java.core.entity.monsters.Skeleton;
import edu.itogproect.java.core.entity.trade.Merchant;
import edu.itogproect.java.core.entity.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static Player player;
    public static BufferedReader bufferedReader;
    public static BattleScene battleScene;
    public static boolean isBattleSceneUse = false;
    public static boolean isTrading = false;

    public static void main(String[] args) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new BattleScene();
        System.out.print("Введите имя персонажа: ");
        command(bufferedReader.readLine());
    }

    private static void command(String s) throws IOException {
        if(isTrading) {
            trading(s);
            return;
        }
        if(s == null || s.isEmpty()) {
            System.out.println("Вы ввели пустую строку");
            command(bufferedReader.readLine());
            return;
        } if(player == null) {
            player = new Player(s, 100, 20, 20, 0, 0);
            System.out.println(String.format("Спасти наш мир от драконов вызвался %s! Да будет его броня крепка и бицепс кругл!", player.getName()));
            printNavigation();
            command(bufferedReader.readLine());
            return;
        }

        switch (s) {
            case "1":
                if(isBattleSceneUse) {
                    System.out.println("Вы находитесь в битве сударь я не могу с вами торговать!");
                    break;
                }
                Merchant.getSellItem(System.out);
                System.out.println("Вы начали торговлю с торговцем, что бы закончить разговор введите exit");
                isTrading = true;
                break;
            case "2":
                commitFight();
                break;
            case "3":
                System.out.println(player.toStringAll());
                break;
            case "4":
                System.exit(1);
            case "да":
                command("2");
                break;
            case "нет":
                printNavigation();
                break;
            default:
                break;
        }
        command(bufferedReader.readLine());
    }

    private static void trading(String s) throws IOException {
        if(s.equalsIgnoreCase("exit")) {
            isTrading = false;
            command(bufferedReader.readLine());
            return;
        }

        String[] strings = s.split(" ");
        String string = strings[0];
        int amount = 1;
        if(strings.length > 1) {
            try {
                amount = Integer.parseInt(strings[1]);
            } catch (NumberFormatException _) {}
        }

        try {
            Merchant.SellItem item = Merchant.SellItem.valueOf(string.toUpperCase());
            int suma = player.getGold() - (item.getPrice() * amount);
            if(suma < 0) {
                System.out.println("У вас не хватает денег на покупку");
                command(bufferedReader.readLine());
                return;
            }
            String doo = player.toStringAll();
            player.setGold(suma);
            UseItem.use(player, item, amount);
            System.out.println("Вы успешно всё сели");
            System.out.println("до " + doo);
            System.out.println("после " + player.toStringAll());
            command(bufferedReader.readLine());
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Такова товара не существует");
            command(bufferedReader.readLine());
            return;
        }
    }

    private static void commitFight() {
        if(isBattleSceneUse) return;
        isBattleSceneUse = true;
        battleScene.fight(player, createMonster(), new FightCallback() {

            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.", player.getName(), player.getXp(), player.getGold(), player.getHealth()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                isBattleSceneUse = false;
                try {
                    command(bufferedReader.readLine());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            @Override
            public void fightLost() {
                isBattleSceneUse = false;
            }
        });
    }

    private static Entity createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin("Гоблин", 50, 10, 10, 100, 20);
        else return new Skeleton("Скелет", 25, 20, 20, 100, 10);
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Информация о вас");
        System.out.println("4. Выход");
    }
}
