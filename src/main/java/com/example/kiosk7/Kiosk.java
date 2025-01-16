package com.example.kiosk7;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Kiosk {
    // 필드
    private final List <Menu> menuList;

    // 생성자
    public Kiosk(List <Menu> menuList) {
        this.menuList = menuList;
    }

    // 메서드

    public List<Menu> getMenuList() {
        return menuList;
    }
    public Menu getOneMenu(int index) {
        return menuList.get(index);
    }
    public void start() {
        Scanner sc = new Scanner(System.in);
        Basket basket = new Basket();
        while (true) {
            // 카테고리를 보여주는 메인 메뉴 출력
            System.out.println("[ MAIN MENU ]");
            int count = 1;
            for (Menu menu : getMenuList()) {
                System.out.println(count+". "+menu.getCategory());
                count++;
            }
            System.out.println("0. 종료     | 종료 ");

            // 장바구니 구현
            if (basket.getBasketList().size() > 0) {
                System.out.println();
                System.out.println("[ ORDER MENU ]");
                System.out.println(count+". Orders               | 장바구니를 확인 후 주문합니다.");
                count++;
                System.out.println(count+". Cancel               | 진행중인 주문을 취소합니다.");
            }

            int max = getMenuList().size() + (basket.getBasketList().size() > 0 ? 2 : 0);
            int n = -1;
            try {
                n = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("문자가 아닌 숫자를 입력해주세요.");
                System.out.println();
                sc.nextLine();
                continue;
            }
            if (n == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (n > max || n < 0) {
                System.out.println("잘못된 번호를 입력했습니다.");
            } else if (n == getMenuList().size() + 1) {
                System.out.println();
                System.out.println("아래와 같이 주문 하시겠습니까?");
                System.out.println();
                while (true) {
                    System.out.println("[ Orders ]");
                    basket.printBasketItem();
                    System.out.println();
                    System.out.println("[ Total ]");
                    System.out.println("W " + basket.totalPrice());
                    System.out.println();
                    System.out.println("1. 주문  2. 메뉴 수정  3. 메뉴판");
                    int num = -1;
                    try {
                        num = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("문자가 아닌 숫자를 입력해주세요.");
                        System.out.println();
                        sc.nextLine();
                        continue;
                    }
                    int discountPercent = -1;
                    if (num == 1) {
                        while (true) {
                            System.out.println("\n할인 정보를 입력해주세요");
                            System.out.println("1. 국가유공자 : 10% ");
                            System.out.println("2. 군인      :  5%");
                            System.out.println("3. 학생      :  3%");
                            System.out.println("4. 일반      :  0%");
                            int userNum = -1;

                            try {
                                userNum = sc.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("문자가 아닌 숫자를 입력해주세요.");
                                sc.nextLine();
                                continue;
                            }
                            UserType userType = UserType.fromCode(userNum);
                            if (userType == null) {
                                System.out.println("잘못된 번호를 입력했습니다.");
                                continue;
                            }
                            discountPercent = userType.getDiscount();
                            break;
                        }

                        System.out.println("주문이 완료되었습니다. 금액은 W " + basket.totalPrice() * (100 - discountPercent) / 100 + "입니다.");
                        basket.resetBasket();
                        System.out.println();
                        break;
                    } else if (num == 2) {
                        int deleteNum = -1;
                        while (true) {
                            try {
                                System.out.println("삭제하실 메뉴의 번호를 눌러주세요.");
                                basket.printBasketItemAndCount();
                                System.out.println("0. 메뉴판");
                                deleteNum = sc.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("문자가 아닌 숫자를 입력해주세요.");
                                sc.nextLine();
                                continue;
                            }
                            if (basket.getBasketList().size() < deleteNum || deleteNum < 0) {
                                System.out.println("잘못된 번호를 입력했습니다.");
                                continue;
                            } else if (deleteNum == 0) {
                                break;
                            } else {
                                basket.removeBasket(deleteNum);
                                break;
                            }
                        }
                        if (deleteNum == 0 || basket.getBasketList().size() == 0) {
                            break;
                        }
                    } else if (num == 3) {
                        break;
                    }
                    System.out.println();
                }


            } else if (n == getMenuList().size() + 2) {
                basket.resetBasket();
                System.out.println();
                System.out.println("진행 중인 주문을 취소했습니다.");
            } else {
                // 선택한 상위 카테고리 메뉴의 세부 메뉴 출력
                // 캡슐화 진행 전 레벨이기 때문에 단순하게 다른 클래스의 필드에 직접 접근
                System.out.println();
                System.out.println("[ "+ getOneMenu(n-1).getCategory()+" ]");
                getOneMenu(n-1).printMenuItems();
                System.out.println("0. 뒤로가기");
                int m = -1;
                try {
                    m = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("문자가 아닌 숫자를 입력해주세요.");
                    System.out.println();
                    sc.nextLine();
                    continue;
                }
                if (m == 0) {
                    System.out.println();
                    continue;
                } else if (m > getOneMenu(n-1).getMenuItems().size() || m < 0) {
                    System.out.println("잘못된 번호를 입력했습니다.");
                } else {
                    System.out.printf("선택한 메뉴 : %s | W %.1f | %s\n",
                            getOneMenu(n-1).getMenuItem(m-1).getName(),
                            getOneMenu(n-1).getMenuItem(m-1).getPrice(),
                            getOneMenu(n-1).getMenuItem(m-1).getText());

                    System.out.println();
                    System.out.printf("\"%s | W %.1f | %s\"\n",
                            getOneMenu(n-1).getMenuItem(m-1).getName(),
                            getOneMenu(n-1).getMenuItem(m-1).getPrice(),
                            getOneMenu(n-1).getMenuItem(m-1).getText());
                    System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                    while (true) {
                        System.out.println("1. 확인         2. 취소");
                        int l = -1;
                        try {
                            l = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("문자가 아닌 숫자를 입력해주세요.");
                            sc.nextLine();
                            continue;
                        }
                        if (l == 1) {
                            boolean flag = false;
                            for (int i = 0; i < basket.getBasketList().size(); i++) {
                                if (basket.getOneBasket(i).getName().equals(getOneMenu(n-1).getMenuItem(m-1).getName())) {
                                    basket.getOneBasket(i).addCount();
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag) {
                                basket.addBasket( new BasketItem(getOneMenu(n-1).getMenuItem(m-1).getName(),
                                        getOneMenu(n-1).getMenuItem(m-1).getPrice(),
                                        getOneMenu(n-1).getMenuItem(m-1).getText(), 1));
                            }
                            System.out.println(getOneMenu(n-1).getMenuItem(m-1).getName() + " 이 장바구니에 추가되었습니다.");
                            break;
                        } else if (l == 2) {
                            break;
                        } else {
                            System.out.println("잘못된 번호를 입력했습니다.");
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}
