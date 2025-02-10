package edu.itogproect.java.core.entity.monsters;

import edu.itogproect.java.core.entity.Fighter;
import edu.itogproect.java.core.entity.Entity;

import java.util.Optional;

public class Monsters extends Entity {

    public Monsters(String name, int health, int strength, int dexterity, int gold, int xp) {
        super(name, health, strength, dexterity, gold, xp);
    }
}
