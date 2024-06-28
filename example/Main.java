package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int cowardCount = 0;
    public static boolean unluckyEffectActive = false;
    public static int encounterCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean playAgain = true;
        while (playAgain) {
            Gladiator gladiator = createGladiator(scanner);
            boolean gameActive = true;
            Random rand = new Random();

            while (gameActive) {
                Monster monster;

                if (encounterCount < 3) {
                    monster = new Monster("goblin", rand.nextInt(gladiator.getLevel()) + 1);
                } else {
                    String[] monsterTypes = {"wolf", "goblin", "ogr", "bear", "dark elf", "chupacabra"};
                    String monsterName = monsterTypes[rand.nextInt(monsterTypes.length)];
                    int monsterLevel = rand.nextInt(gladiator.getLevel()) + 1;

                    monster = new Monster(monsterName, monsterLevel);
                }

                encounterCount++;
                System.out.println("Ви зустріли " + monster.getName() + " рівня " + monster.getLevel() + "!");

                boolean actionChosen = false;
                while (!actionChosen) {
                    displayActionsMenu();

                    int action = getUserChoice(scanner);
                    switch (action) {
                        case 0:
                            openMenu(scanner);
                            break;
                        case 1:
                            actionChosen = true;
                            startBattle(scanner, rand, gladiator, monster);
                            break;
                        case 2:
                            if (attemptNegotiation(rand)) {
                                actionChosen = true;
                            } else {
                                Fine.Unlucky unluckyFine = new Fine.Unlucky();
                                unluckyFine.imposeFine(gladiator);
                            }
                            break;
                        case 3:
                            if (attemptEscape(rand, gladiator)) {
                                actionChosen = true;
                            } else {
                                cowardCount = 0;
                            }
                            break;
                        case 4:
                            gladiator.getInventory().showInventory();
                            break;
                        case 5:
                            Trader trader = new Trader();
                            trader.interact(gladiator);
                            break;
                        default:
                            System.out.println("Некоректний вибір. Спробуйте ще раз.");
                    }
                }

                if (!gladiator.isAlive()) {
                    gameActive = false;
                    playAgain = handleDeath(scanner);
                }
            }
        }

        scanner.close();
    }

    private static boolean handleDeath(Scanner scanner) {
        System.out.println("Ви померли. Оберіть дію:");
        System.out.println("1. Почати спочатку");
        System.out.println("2. Відкрити меню");

        int deathChoice = getUserChoice(scanner);

        switch (deathChoice) {
            case 1:
                System.out.println("Починаємо нову гру...");
                encounterCount = 0;
                cowardCount = 0;
                unluckyEffectActive = false;
                return true;
            case 2:
                openMenu(scanner);
                return false;
            default:
                System.out.println("Некоректний вибір. Гра завершена.");
                System.exit(0);
                return false;
        }
    }

    private static Gladiator createGladiator(Scanner scanner) {
        System.out.println("Види гладіаторів:");
        System.out.println("1. Gall - Має високу витривалість і силу, маленька харизма.");
        System.out.println("2. Dymakher - Має високу спритність і критичний шанс та велику харизму.");
        System.out.println("3. Thraex - Має більше хепе та середню силу, не має критичного шансу та повільніший.");

        System.out.print("Виберіть тип гладіатора: ");
        int choice = getUserChoice(scanner);

        switch (choice) {
            case 1:
                return new Gall(10, 9, 0, 7, 1, 6, 15, 4, 90, true, 100, true);
            case 2:
                return new Dymakher(8, 7, 0, 10, 1, 7, 15, 7, 60, false, 100, false);
            case 3:
                return new Thraex(8, 8, 0, 4, 1, 7, 15, 0, 110, false, 100, false);
            default:
                System.out.println("Некоректний вибір. Гра завершена.");
                System.exit(0);
                return null;
        }
    }

    private static void displayActionsMenu() {
        System.out.println("Обери дію:");
        System.out.println("1. Бій");
        System.out.println("2. Домовитися");
        System.out.println("3. Втікти");
        System.out.println("4. Відкрити інвентар");
        System.out.println("5. Торговець");
        System.out.println("0. Меню");
    }

    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Некоректний вибір. Введіть число:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void openMenu(Scanner scanner) {
        boolean menuActive = true;
        while (menuActive) {
            System.out.println("Меню:");
            System.out.println("1. Продовжити");
            System.out.println("2. Налаштування");
            System.out.println("3. Вийти");

            int menuChoice = getUserChoice(scanner);
            switch (menuChoice) {
                case 1:
                    menuActive = false;
                    break;
                case 2:
                    System.out.println("Тут будуть налаштування (поки не реалізовано).");
                    break;
                case 3:
                    System.out.println("Вихід з гри.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некоректний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static boolean attemptNegotiation(Random rand) {
        if (rand.nextInt(100) < (unluckyEffectActive ? 100 : 50)) {
            System.out.println("Вам вдалося домовитися!");
            unluckyEffectActive = false;
            return true;
        } else {
            System.out.println("Домовитися не вдалося.");
            return false;
        }
    }

    private static boolean attemptEscape(Random rand, Gladiator gladiator) {
        if (rand.nextInt(100) < 60) {
            System.out.println("Вам вдалося втекти!");
            cowardCount++;
            if (cowardCount >= 2) {
                Fine.Coward cowardFine = new Fine.Coward();
                cowardFine.imposeFine(gladiator);
            }
            return true;
        } else {
            System.out.println("Втекти не вдалося.");
            return false;
        }
    }

    private static void startBattle(Scanner scanner, Random rand, Gladiator gladiator, Monster monster) {
        System.out.println("Битва починається!");

        boolean battleActive = true;
        while (battleActive && gladiator.isAlive() && monster.isAlive()) {
            System.out.println("Обери дію:");
            System.out.println("1. Атакувати");
            System.out.println("2. Блокувати");

            int battleAction = getUserChoice(scanner);
            switch (battleAction) {
                case 1:
                    int gladiatorDamage = gladiator.attack();
                    System.out.println("Гладіатор завдав " + gladiatorDamage + " шкоди монстру.");
                    monster.defend(gladiatorDamage);

                    if (monster.isAlive()) {
                        int monsterDamage = monster.getAttack();
                        System.out.println("Монстр завдав " + monsterDamage + " шкоди гладіатору.");
                        gladiator.defend(monsterDamage);
                    }
                    break;
                case 2:
                    System.out.println("Гладіатор блокує атаку!");
                    int blockSuccess = rand.nextInt(100);
                    if (blockSuccess < 50) {
                        System.out.println("Блокування було успішним!");
                    } else {
                        System.out.println("Блокування не вдалося!");
                        int monsterDamage = monster.getAttack();
                        System.out.println("Монстр завдав " + monsterDamage + " шкоди гладіатору.");
                        gladiator.defend(monsterDamage);
                    }
                    break;
                default:
                    System.out.println("Некоректний вибір. Спробуйте ще раз.");
            }

            if (!monster.isAlive()) {
                System.out.println("Гладіатор переміг!");
                gladiator.levelUp();
                gladiator.addGold(monster.getGoldDropped());
                battleActive = false;
            } else if (!gladiator.isAlive()) {
                System.out.println("Монстр переміг!");
                battleActive = false;
            }
        }
    }
}