package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Trader {
    private Map<String, Integer> inventory;

    public Trader() {
        inventory = new HashMap<>();
        inventory.put("Revival Crystal", 40);  // Змінена ціна на 40 золотих
        inventory.put("Unity Crystal", 25);    // Змінена ціна на 25 золотих
        inventory.put("Master Crystal", 100);  // Змінена ціна на 100 золотих
    }

    public void interact(Gladiator gladiator) {
        System.out.println("Торговець зустрівся з вами!");
        System.out.println("Торговець пропонує завдання: Зібрати 3 шкури ведмедя та 5 шкур вовка.");
        System.out.println("Приймаєте завдання? (так/ні)");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();

        if (answer.equalsIgnoreCase("так")) {
            System.out.println("Торговець дякує вам за згоду. Удачі в полюванні!");
            // Тут можна додати код для завдання гравцю
        } else {
            System.out.println("Торговець розчарований вашим відмовою.");
            Fine.Heartless heartlessFine = new Fine.Heartless();
            heartlessFine.imposeFine(gladiator);
        }

        System.out.println("Торговець також продає кілька предметів за золоті монети:");
        System.out.println("1. Камінь регенерації (ціна: 15 золотих)");
        System.out.println("2. Unity Crystal (ціна: " + inventory.get("Unity Crystal") + " золотих)");
        System.out.println("3. Revival Crystal (ціна: " + inventory.get("Revival Crystal") + " золотих)");
        System.out.println("4. Master Crystal (ціна: " + inventory.get("Master Crystal") + " золотих)");
        System.out.println("Виберіть номер предмету, який бажаєте купити (або натисніть 0 для виходу):");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                if (gladiator.getGold() >= 15) {
                    gladiator.subtractGold(15);
                    gladiator.getInventory().addItem("Камінь регенерації");
                    System.out.println("Ви купили камінь регенерації.");
                } else {
                    System.out.println("У вас недостатньо золота для покупки.");
                }
                break;
            case 2:
                if (gladiator.getGold() >= inventory.get("Unity Crystal")) {
                    gladiator.subtractGold(inventory.get("Unity Crystal"));
                    gladiator.getInventory().addItem("Unity Crystal");
                    System.out.println("Ви купили Unity Crystal.");
                } else {
                    System.out.println("У вас недостатньо золота для покупки.");
                }
                break;
            case 3:
                if (gladiator.getGold() >= inventory.get("Revival Crystal")) {
                    gladiator.subtractGold(inventory.get("Revival Crystal"));
                    gladiator.getInventory().addItem("Revival Crystal");
                    System.out.println("Ви купили Revival Crystal.");
                } else {
                    System.out.println("У вас недостатньо золота для покупки.");
                }
                break;
            case 4:
                if (gladiator.getGold() >= inventory.get("Master Crystal")) {
                    gladiator.subtractGold(inventory.get("Master Crystal"));
                    gladiator.getInventory().addItem("Master Crystal");
                    System.out.println("Ви купили Master Crystal.");
                } else {
                    System.out.println("У вас недостатньо золота для покупки.");
                }
                break;
            case 0:
                System.out.println("До побачення!");
                break;
            default:
                System.out.println("Некоректний вибір.");
        }
    }
}