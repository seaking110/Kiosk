package com.example.kiosk6;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    // 필드
    private final List<BasketItem> basketList = new ArrayList<>();

    // 메서드

    // 장바구니 리스트에 하나의 메뉴를 추가하는 메서드
    public void addBasket(BasketItem item){
        getBasketList().add(item);
    }

    // getter
    public List<BasketItem> getBasketList() {
        return basketList;
    }

    // 장바구니 리스트의 해당 인덱스에 해당하는 메뉴를 반환하는 메서드
    public BasketItem getOneBasket(int index) {
        return getBasketList().get(index);
    }

    // 장바구니를 초기화 하는 메서드
    public void resetBasket() {
        getBasketList().clear();
    }

    // 장바구니를 출력하는 메서드
    public void printBasketItem() {
        for (BasketItem item : getBasketList()) {
            System.out.printf("%-20s | W %.1f | %s X %d\n", item.getName(), item.getPrice(), item.getText(), item.getCount());
        }
    }

    // 장바구니의 제품의 총 가격을 계산하는 메서드
    // 소수점 둘째자리에서 반올림 처리
    public double totalPrice() {
        double sum = 0;
        for (BasketItem item : getBasketList()) {
            sum += item.getPrice() * item.getCount();
        }
        sum = (double) Math.round(sum  * 10) / 10;
        return sum;
    }
}
