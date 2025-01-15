package com.example.kiosk2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List <MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Thigh Burger",4.9, "바삭한 치킨 패티와 양상추가 조화를 이루는 맘스터치 시그니처 버거."));
        menuItems.add(new MenuItem("Fire Thigh Burger",5.1, "불맛이 살아있는 버거, 싸이버거의 매운맛 버전."));
        menuItems.add(new MenuItem("White Garlic Burger",5.5, "화이트 갈릭소스에 더블햄과 통가슴살 패티까지 담은 버거."));
        menuItems.add(new MenuItem("Deep Cheese Burger",5.4, "부드러운 치즈와 통가슴살 패티 버거."));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("[ MOMS TOUCH MENU ]");
            int count = 1;
            // list에 있는 값을 반복문으로 가져와 출력, 문자열의 길이를 맞추기 위해 문자열 포맷팅 사용
            for (MenuItem menu :menuItems) {
                System.out.printf("%d. %-20s | W %.1f | %s\n", count, menu.name, menu.price, menu.text);
                count++;
            }
            System.out.println("0. 종료     | 종료 ");
            int n = sc.nextInt();
            // 입력 값에 따른 결과 처리
            if (n == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (n > menuItems.size()) {
                System.out.println("잘못된 번호를 입력했습니다.");
            } else {
                System.out.printf("선택한 메뉴 : %s | W %.1f | %s\n",menuItems.get(n-1).name, menuItems.get(n-1).price, menuItems.get(n-1).text);
            }
        }
    }
}
