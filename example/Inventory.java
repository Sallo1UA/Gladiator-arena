package org.example;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<String> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void showInventory() {
        System.out.println("Ваш інвентар:");
        for (String item : items) {
            System.out.println("- " + item);
        }
    }
}
