package com.example.kiosk5;

public class MenuItem {
    //필드
    String name;
    double price;
    String text;

    //생성자
    public MenuItem(String name, double price, String text) {
        this.name = name;
        this.price = price;
        this.text = text;
    }

    // 메서드
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    public String getText() {
        return text;
    }
}
