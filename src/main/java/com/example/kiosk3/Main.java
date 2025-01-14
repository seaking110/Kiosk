package com.example.kiosk3;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Thigh Burger",4.9, "바삭한 치킨 패티와 양상추가 조화를 이루는 맘스터치 시그니처 버거."));
        menuItems.add(new MenuItem("Fire Thigh Burger",5.1, "불맛이 살아있는 버거, 싸이버거의 매운맛 버전."));
        menuItems.add(new MenuItem("White Garlic Burger",5.5, "화이트 갈릭소스에 더블햄과 통가슴살 패티까지 담은 버거."));
        menuItems.add(new MenuItem("Deep Cheese Burger",5.4, "부드러운 치즈와 통가슴살 패티 버거."));
        Kiosk kiosk = new Kiosk(menuItems);
        kiosk.start();



    }
}
