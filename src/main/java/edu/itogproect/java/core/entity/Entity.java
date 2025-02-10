package edu.itogproect.java.core.entity;

public abstract class Entity implements Fighter {
    String name;
    int health, strength, dexterity, gold, xp;

    public Entity() {
 // Временно что бы не видеть ошибок
    }

    public Entity(String name, int health, int strength, int dexterity, int gold, int xp) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.dexterity = dexterity;
        this.gold = gold;
        this.xp = xp;
    }

    @Override
    public synchronized int attack() {
        return dexterity * 5 > random() ? strength : 0;
        //if(dexterity * 3 > random()) return strength;
        //return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    private static int random() {
        return (int) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, health);
    }

    public String toStringAll() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", gold=" + gold +
                ", xp=" + xp +
                '}';
    }
}
