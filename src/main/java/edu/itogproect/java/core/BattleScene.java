package edu.itogproect.java.core;

import edu.itogproect.java.core.entity.Entity;
import edu.itogproect.java.core.entity.player.Player;

import java.util.concurrent.atomic.AtomicBoolean;

public class BattleScene {

    public int turn;
    public boolean end = true;

    public void fight(Entity player, Entity monster, FightCallback callback) {
        new Thread(() -> {
            turn = 1;
            end = false;
            while (!end) { // Если false тогда продолжаем
                System.out.println("----Ход: " + turn + "----");
                if (turn++ % 2 != 0) {
                    end = hit(player, monster, callback);
                } else {
                    end = hit(monster, player, callback);
                }
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }

    private boolean hit(Entity attacker, Entity defender, FightCallback callback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHealth() - hit;
        if (defenderHealth <= 0) defenderHealth = 0;
        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth));
            if (defenderHealth == 0) {
                System.out.println("--------------");
            }
        } else {
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Player) {
            System.out.println("Извините, вы пали в бою...");
            callback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getXp(), defender.getGold()));
            attacker.setGold(attacker.getGold() + defender.getGold());
            attacker.setXp(attacker.getXp() + defender.getXp());
            callback.fightWin();
            return true;
        } else {
            defender.setHealth(defenderHealth);
            return false;
        }
    }
}