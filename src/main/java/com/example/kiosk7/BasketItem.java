package com.example.kiosk7;

public class BasketItem {
    //필드
    private final String name;

    private final double price;

    private final String text;

    private  int count;

    //생성자
    public BasketItem(String name, double price, String text, int count) {
        this.name = name;
        this.price = price;
        this.text = text;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void addCount() {
        count++;
    }

}
