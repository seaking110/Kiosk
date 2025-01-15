package com.example.kiosk4;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    // 필드
    List<MenuItem> menuItems = new ArrayList<>();
    String category;

    // 생성자
    public Menu(String category) {
        this.category = category;
    }


    // 메서드
    // 요구사항. 카테고리 이름을 반환하는 메서드 구현
    public String getCategory() {
        return category;
    }
    public void printMenuItems() {
        int count = 1;
        for (MenuItem item : menuItems) {
            System.out.printf("%d. %-20s | W %.1f | %s\n", count, item.name, item.price, item.text);
            count++;
        }
    }
}
