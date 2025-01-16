package com.example.kiosk7;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List <Menu> menu = new ArrayList<>();
        Menu burgerMenu = new Menu("Burgers");
        menu.add(burgerMenu);
        burgerMenu.addMenuItem(new MenuItem("Thigh Burger",4.9, "치킨 패티와 양상추가 조화를 이루는 시그니처 버거."));
        burgerMenu.addMenuItem(new MenuItem("Fire Thigh Burger",5.1, "불맛이 살아있는 버거, 싸이버거의 매운맛 버전."));
        burgerMenu.addMenuItem(new MenuItem("White Garlic Burger",5.5, "화이트 갈릭소스와 더블햄 통가슴살 패티까지 담은 버거."));
        burgerMenu.addMenuItem(new MenuItem("Deep Cheese Burger",5.4, "부드러운 치즈와 통가슴살 패티 버거."));
        Menu drinkMenu = new Menu("Drinks");
        menu.add(drinkMenu);
        drinkMenu.addMenuItem(new MenuItem("Coke",1.8, "시원한 콜라."));
        drinkMenu.addMenuItem(new MenuItem("Zero Coke",2.0, "시원한 제로 콜라."));
        drinkMenu.addMenuItem(new MenuItem("Sprite", 1.8, "시원한 스프라이트"));
        drinkMenu.addMenuItem(new MenuItem("Fanta Orange",1.8, "시원한 오렌지 맛 환타."));
        Menu dessertMenu = new Menu("Desserts");
        menu.add(dessertMenu);
        dessertMenu.addMenuItem(new MenuItem("Cajun Potatoes",2.0, "케이준 스타일의 바삭한 양념감자."));
        dessertMenu.addMenuItem(new MenuItem("Cheese Stick",2.0, "모짜렐라 치즈스틱."));
        dessertMenu.addMenuItem(new MenuItem("Jalapeno Nuggets", 2.0, "할라피뇨로 매콤하게 즐기는 할라피뇨 너겟"));
        dessertMenu.addMenuItem(new MenuItem("Popcorn Ball",2.6, "바삭한 오리지널 팝콘볼."));
        dessertMenu.addMenuItem(new MenuItem("Corn Salad",1.8, "달콤한 스위트콘에 신선한 야채까지."));
        Kiosk kiosk = new Kiosk(menu);
        kiosk.start();
    }
}
