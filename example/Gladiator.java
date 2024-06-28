package org.example;

public class Gladiator {
    private int constitution;
    private int strength;
    private int xp;
    private int dexterity;
    private int level;
    private int basicAttack;
    private int basicHP;
    private int critChance;
    private int charisma;
    private boolean haveShield;
    private int gold;
    private boolean hasRevivalCrystal;
    private Inventory inventory;
    private boolean alive;

    public Gladiator(int constitution, int strength, int xp, int dexterity, int level, int basicAttack, int basicHP, int critChance, int charisma, boolean haveShield, int gold, boolean hasRevivalCrystal) {
        this.constitution = constitution;
        this.strength = strength;
        this.xp = xp;
        this.dexterity = dexterity;
        this.level = level;
        this.basicAttack = basicAttack;
        this.basicHP = basicHP;
        this.critChance = critChance;
        this.charisma = charisma;
        this.haveShield = haveShield;
        this.gold = gold;
        this.hasRevivalCrystal = hasRevivalCrystal;
        this.alive = true;
        this.inventory = new Inventory();
    }

    public int getGold() {
        return gold;
    }

    public void subtractGold(int amount) {
        gold -= amount;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int attack() {
        return strength;
    }

    public void defend(int damage) {
        basicHP -= damage;
        if (basicHP <= 0) {
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void levelUp() {
        level++;
        basicHP += 10;
        strength += 5;
        dexterity += 5;
    }

    public int getLevel() {
        return level;
    }

    public void addCrystal(String crystalType) {
        inventory.addItem(crystalType);
    }
}