package com.example.kiosk1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("[ MOMS TOUCH MENU ]");
            System.out.println("1. Thigh Burger        | W 4.9 | 바삭한 치킨 패티와 양상추가 조화를 이루는 맘스터치 시그니처 버거.");
            System.out.println("2. Fire Thigh Burger   | W 5.1 | 불맛이 살아있는 버거, 싸이버거의 매운맛 버전.");
            System.out.println("3. White Garlic Burger | W 5.5 | 화이트 갈릭소스에 더블햄과 통가슴살 패티까지 담은 버거.");
            System.out.println("4. Deep Cheese Burger  | W 5.4 | 부드러운 치즈와 통가슴살 패티 버거.");
            System.out.println("0. 종료     | 종료 ");
            int n = sc.nextInt();
            // swich 문 이용, 지정된 번호가 아닐 시 default로 처리
            switch (n) {
                case 1 -> System.out.println("Thigh Burger 주문!!");
                case 2 -> System.out.println("Fire Thigh Burger 주문!!");
                case 3 -> System.out.println("White Garlic Burger 주문!!");
                case 4 -> System.out.println("Deep Cheese Burger 주문!!");
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    //break를 넣을 시 switch문에서만 나가 질뿐 while문에선 안나가지므로 return을 사용하여 main 메서드 종료
                    return;
                }
                default -> {
                    System.out.println("잘못된 번호를 입력하셨습니다.");
                }
            }
        }
    }
}
