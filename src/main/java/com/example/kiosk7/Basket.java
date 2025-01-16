package com.example.kiosk7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void removeBasket(int index) {
        List<BasketItem> updatedList = getBasketList().stream()
                .filter(item -> getBasketList().indexOf(item) != index - 1) // index 조건 제외
                .toList();
        getBasketList().clear(); // 원본 리스트를 비움
        getBasketList().addAll(updatedList); // 새 리스트를 추가
    }
    public void printBasketItem() {
        for (BasketItem item : getBasketList()) {
            System.out.printf("%-20s | W %.1f | %s X %d\n", item.getName(), item.getPrice(), item.getText(), item.getCount());
        }
    }

    public void printBasketItemAndCount() {
        int count = 1;
        for (BasketItem item : getBasketList()) {
            System.out.printf("%d. %-20s | W %.1f | %s X %d\n", count , item.getName(), item.getPrice(), item.getText(), item.getCount());
            count++;
        }
    }

    public double totalPrice() {
        double sum = 0;
        for (BasketItem item : getBasketList()) {
            sum += item.getPrice() * item.getCount();
        }
        sum = (double) Math.round(sum  * 10) / 10;
        return sum;
    }
}
