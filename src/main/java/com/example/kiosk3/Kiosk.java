package com.example.kiosk3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    List<MenuItem> menuItems;

    public Kiosk (List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void start(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("[ MOMS TOUCH MENU ]");
            int count = 1;
            // list에 있는 값을 반복문으로 가져와 출력, 문자열의 길이를 맞추기 위해 문자열 포맷팅 사용
            for (MenuItem menu : menuItems) {
                System.out.printf("%d. %-20s | W %.1f | %s\n",count,menu.name, menu.price,menu.text);
                count++;
            }
            System.out.println("0. 종료     | 종료 ");
            // 숫자가 아닌 문자가 들어 왔을 때의 예외처리
            try {
                int n = sc.nextInt();
                // 입력 값에 따른 결과 처리
                if (n == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                } else if (n > menuItems.size()) {
                    System.out.println("잘못된 번호를 입력했습니다.");
                } else {
                    System.out.printf("선택한 메뉴 : %s | W %.1f | %s\n",menuItems.get(n-1).name, menuItems.get(n-1).price,menuItems.get(n-1).text);
                }
            } catch (InputMismatchException e) {
                System.out.println("문자가 아닌 숫자를 입력해주세요.");
                sc.nextLine();
            }

        }
    }
}
