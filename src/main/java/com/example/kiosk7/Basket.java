package com.example.kiosk7;

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

    // 장바구니의 특정 인덱스를 삭제하는 메서드
    public void removeBasket(int index) {
        List<BasketItem> updatedList = getBasketList().stream()
                .filter(item -> getBasketList().indexOf(item) != index - 1) // index 조건 제외
                .toList();
        getBasketList().clear(); // 원본 리스트를 비움
        getBasketList().addAll(updatedList); // 새 리스트를 추가
    }
    // 장바구니를 출력하는 메서드
    public void printBasketItem() {
        for (BasketItem item : getBasketList()) {
            System.out.printf("%-20s | W %.1f | %s X %d\n", item.getName(), item.getPrice(), item.getText(), item.getCount());
        }
    }

    // 번호를 붙인 장바구니 출력 메서드
    public void printBasketItemAndCount() {
        int count = 1;
        for (BasketItem item : getBasketList()) {
            System.out.printf("%d. %-20s | W %.1f | %s X %d\n", count , item.getName(), item.getPrice(), item.getText(), item.getCount());
            count++;
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
