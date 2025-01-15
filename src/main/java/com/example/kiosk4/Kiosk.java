package com.example.kiosk4;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Kiosk {
    // 필드
    List <Menu> menuList;

    // 생성자
    public Kiosk(List <Menu> menuList) {
        this.menuList = menuList;
    }

    // 메서드
    public void start(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            // 카테고리를 보여주는 메인 메뉴 출력
            System.out.println("[ MAIN MENU ]");
            int count = 1;
            for (Menu menu : menuList) {
                System.out.println(count+". "+menu.getCategory());
                count++;
            }
            System.out.println("0. 종료     | 종료 ");
            try {
                int n = sc.nextInt();
                if (n == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                } else if (n > menuList.size()) {
                    System.out.println("잘못된 번호를 입력했습니다.");
                } else {
                    // 선택한 상위 카테고리 메뉴의 세부 메뉴 출력
                    // 캡슐화 진행 전 레벨이기 때문에 단순하게 다른 클래스의 필드에 직접 접근
                    System.out.println();
                    System.out.println("[ "+menuList.get(n-1).getCategory()+" ]");
                    menuList.get(n-1).printMenuItems();
                    System.out.println("0. 뒤로가기");
                    int m = sc.nextInt();
                    if (m == 0) {
                        System.out.println();
                        continue;
                    } else if (m > menuList.get(n-1).menuItems.size() || m < 0) {
                        System.out.println("잘못된 번호를 입력했습니다.");
                    } else {
                        System.out.printf("선택한 메뉴 : %s | W %.1f | %s\n",
                                menuList.get(n-1).menuItems.get(m-1).name, menuList.get(n-1).menuItems.get(m-1).price, menuList.get(n-1).menuItems.get(m-1).text);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("문자가 아닌 숫자를 입력해주세요.");
                sc.nextLine();
            }
            System.out.println();
        }
    }
}
