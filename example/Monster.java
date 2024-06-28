package org.example;

public class Monster {
    private static final int BASE_HP_GOBLIN = 50;
    private static final int BASE_ATTACK_GOBLIN = 10;
    private static final int BASE_HP_WOLF = 60;
    private static final int BASE_ATTACK_WOLF = 12;
    private static final int BASE_HP_OGRE = 100;
    private static final int BASE_ATTACK_OGRE = 20;
    private static final int BASE_HP_BEAR = 80;
    private static final int BASE_ATTACK_BEAR = 18;
    private static final int BASE_HP_DARK_ELF = 70;
    private static final int BASE_ATTACK_DARK_ELF = 15;
    private static final int BASE_HP_CHUPACABRA = 90;
    private static final int BASE_ATTACK_CHUPACABRA = 25;

    private int baseHp;
    private int baseAttack;
    private int currentHp;
    private String name;
    private int level;

    public Monster(String type, int level) {
        this.level = level;
        switch (type.toLowerCase()) {
            case "goblin":
                this.baseHp = BASE_HP_GOBLIN;
                this.baseAttack = BASE_ATTACK_GOBLIN;
                break;
            case "wolf":
                this.baseHp = BASE_HP_WOLF;
                this.baseAttack = BASE_ATTACK_WOLF;
                break;
            case "ogr":
                this.baseHp = BASE_HP_OGRE;
                this.baseAttack = BASE_ATTACK_OGRE;
                break;
            case "bear":
                this.baseHp = BASE_HP_BEAR;
                this.baseAttack = BASE_ATTACK_BEAR;
                break;
            case "dark elf":
                this.baseHp = BASE_HP_DARK_ELF;
                this.baseAttack = BASE_ATTACK_DARK_ELF;
                break;
            case "chupacabra":
                this.baseHp = BASE_HP_CHUPACABRA;
                this.baseAttack = BASE_ATTACK_CHUPACABRA;
                break;
            default:
                throw new IllegalArgumentException("Unknown monster type: " + type);
        }
        this.name = type;
        this.currentHp = calculateHp();
    }

    private int calculateHp() {
        return (int) (baseHp * Math.pow(1.2, level - 1));
    }

    private int calculateAttack() {
        return (int) (baseAttack * Math.pow(1.2, level - 1));
    }

    public int getHp() {
        return currentHp;
    }

    public int getAttack() {
        return calculateAttack();
    }

    public void defend(int damage) {
        currentHp -= damage;
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public int getGoldDropped() {
        return getHp() / 10;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}