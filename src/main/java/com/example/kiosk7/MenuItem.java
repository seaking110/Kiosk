package com.example.kiosk7;

public class MenuItem {
    //필드
    private final String name;
    private final double price;
    private final String text;

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
