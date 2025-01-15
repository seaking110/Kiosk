package com.example.kiosk6;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<BasketItem> basketList = new ArrayList<>();


    public void addBasket(BasketItem item){
        getBasketList().add(item);
    }

    public List<BasketItem> getBasketList() {
        return basketList;
    }

    public BasketItem getOneBasket(int index) {
        return getBasketList().get(index);
    }

    public void resetBasket() {
        getBasketList().clear();
    }

    public void printBasketItem() {
        for (BasketItem item : getBasketList()) {
            System.out.printf("%-20s | W %.1f | %s X %d\n", item.getName(), item.getPrice(), item.getText(), item.getCount());
        }
    }

    public double totalPrice() {
        double sum = 0;
        for (BasketItem item : getBasketList()) {
            sum += item.getPrice() * item.getCount();
        }
        return sum;
    }
}
