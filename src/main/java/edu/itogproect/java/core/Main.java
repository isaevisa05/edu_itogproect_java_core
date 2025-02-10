package edu.itogproect.java.core;

import edu.itogproect.java.core.entity.Entity;
import edu.itogproect.java.core.entity.monsters.Goblin;
import edu.itogproect.java.core.entity.monsters.Skeleton;
import edu.itogproect.java.core.entity.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {

    public static Player player;
    public static BufferedReader bufferedReader;
    public static BattleScene battleScene;
    public static boolean isBattleSceneUse = false;

    public static void main(String[] args) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new BattleScene();
        System.out.print("Введите имя персонажа: ");
        command(bufferedReader.readLine());
    }

    private static void command(String s) throws IOException {
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
                System.out.println("Торговец еще не приехал");
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
