package com.example.kiosk4;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List <Menu> menu = new ArrayList<>();
        Menu burgerMenu = new Menu("Burgers");
        menu.add(burgerMenu);
        burgerMenu.menuItems.add(new MenuItem("Thigh Burger",4.9, "바삭한 치킨 패티와 양상추가 조화를 이루는 맘스터치 시그니처 버거."));
        burgerMenu.menuItems.add(new MenuItem("Fire Thigh Burger",5.1, "불맛이 살아있는 버거, 싸이버거의 매운맛 버전."));
        burgerMenu.menuItems.add(new MenuItem("White Garlic Burger",5.5, "화이트 갈릭소스에 더블햄과 통가슴살 패티까지 담은 버거."));
        burgerMenu.menuItems.add(new MenuItem("Deep Cheese Burger",5.4, "부드러운 치즈와 통가슴살 패티 버거."));
        Menu drinkMenu = new Menu("Drinks");
        menu.add(drinkMenu);
        drinkMenu.menuItems.add(new MenuItem("Coke",1.8, "시원한 콜라."));
        drinkMenu.menuItems.add(new MenuItem("Zero Coke",2.0, "시원한 제로 콜라."));
        drinkMenu.menuItems.add(new MenuItem("Sprite", 1.8, "시원한 스프라이트"));
        drinkMenu.menuItems.add(new MenuItem("Fanta Orange",1.8, "시원한 오렌지 맛 환타."));
        Menu dessertMenu = new Menu("Desserts");
        menu.add(dessertMenu);
        dessertMenu.menuItems.add(new MenuItem("Cajun Potatoes",2.0, "케이준 스타일의 바삭한 양념감자."));
        dessertMenu.menuItems.add(new MenuItem("Cheese Stick",2.0, "모짜렐라 치즈스틱."));
        dessertMenu.menuItems.add(new MenuItem("Jalapeno Nuggets", 2.0, "할라피뇨로 매콤하게 즐기는 할라피뇨 너겟"));
        dessertMenu.menuItems.add(new MenuItem("Popcorn Ball",2.6, "바삭한 오리지널 팝콘볼."));
        dessertMenu.menuItems.add(new MenuItem("Corn Salad",1.8, "달콤한 스위트콘에 신선한 야채까지."));
        Kiosk kiosk = new Kiosk(menu);
        kiosk.start();
    }
}
