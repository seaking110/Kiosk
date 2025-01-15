package com.example.kiosk5;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    // 필드
    private final List<MenuItem> menuItems = new ArrayList<>();
    private final String category;

    // 생성자
    public Menu(String category) {
        this.category = category;
    }


    // 메서드
    public void addMenuItem(MenuItem item){
        menuItems.add(item);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public MenuItem getMenuItem(int index) {
        return menuItems.get(index);
    }

    public String getCategory() {
        return category;
    }
    public void printMenuItems() {
        int count = 1;
        for (MenuItem item : getMenuItems()) {
            System.out.printf("%d. %-20s | W %.1f | %s\n", count, item.getName(), item.getPrice(), item.getText());
            count++;
        }
    }
}
