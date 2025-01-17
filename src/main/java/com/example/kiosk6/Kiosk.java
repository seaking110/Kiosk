package com.example.kiosk6;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Kiosk {
    // 필드
    private final List<Menu> menuList;

    // 생성자
    public Kiosk(List<Menu> menuList) {
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
            printMainMenu(basket);
            // 출력되는 줄 수를 파악하여 해당 값 보다 크면 예외 처리
            int max = getMenuList().size() + (basket.getBasketList().size() > 0 ? 2 : 0);
            int n = input(sc);
            if (n == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (n > max || n < 0) {
                System.out.println("잘못된 번호를 입력했습니다.");
                continue;
            } else if (n == getMenuList().size() + 1) {
                System.out.println("\n아래와 같이 주문 하시겠습니까?\n");
                while (true) {
                    System.out.println("[ Orders ]");
                    basket.printBasketItem();
                    System.out.println("\n[ Total ]");
                    System.out.println("W " + basket.totalPrice() + "\n");
                    System.out.println("1. 주문           2. 메뉴판");
                    int num = input(sc);
                    if (num == 1) {
                        System.out.println("\n주문이 완료되었습니다. 금액은 W " + basket.totalPrice() + "입니다.\n");
                        basket.resetBasket();
                        break;
                    } else if (num == 2) {
                        break;
                    } else {
                        System.out.println("잘못된 번호를 입력했습니다.\n");
                    }
                }
            } else if (n == getMenuList().size() + 2) {
                basket.resetBasket();
                System.out.println("\n진행 중인 주문을 취소했습니다.");
            } else {
                // 상세 메뉴 출력
                while (true) {
                    Menu menu = getOneMenu(n - 1);
                    printDetailMenu(menu);
                    int m = input(sc);
                    if (m == 0) {
                        System.out.println();
                        break;
                    } else if (m > menu.getMenuItems().size() || m < 0) {
                        System.out.println("잘못된 번호를 입력했습니다.\n");
                    } else {
                        MenuItem menuItem = menu.getMenuItem(m - 1);
                        System.out.printf("선택한 메뉴 : %s | W %.1f | %s\n",
                                menuItem.getName(),
                                menuItem.getPrice(),
                                menuItem.getText());
                        boolean flag = askToAddToBasket(sc, menuItem, basket);
                        if (flag) {
                            break;
                        }
                    }

                }
            }
        }
    }
    // 메인 메뉴를 보여주는 메서드
    // 장바구니에 들어가 있는 메뉴가 있다면 Order Menu 추가 출력
    public void printMainMenu(Basket basket) {
        System.out.println("[ MAIN MENU ]");
        int count = 1;
        for (Menu menu : getMenuList()) {
            System.out.println(count + ". " + menu.getCategory());
            count++;
        }
        System.out.println("0. 종료     | 종료 ");
        if (basket.getBasketList().size() > 0) {
            System.out.println("\n[ ORDER MENU ]");
            System.out.println((getMenuList().size() + 1) + ". Orders               | 장바구니를 확인 후 주문합니다.");
            System.out.println((getMenuList().size() + 2) + ". Cancel               | 진행중인 주문을 취소합니다.");
        }
    }

    // 메인 메뉴에서 카테고리를 선택하면 나오는 세부화면을 출력하는 메서드
    public void printDetailMenu(Menu menu) {
        System.out.println("\n[ " + menu.getCategory() + " ]");
        menu.printMenuItems();
        System.out.println("0. 뒤로가기");
    }

    // 사용자의 입력을 받는 메서드
    public int input(Scanner sc) {
        int n = -1;
        while (true) {
            try {
                n = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("문자가 아닌 숫자를 입력해주세요.\n");
                sc.nextLine();
                continue;
            }
            return n;
        }
    }
    // 장바구니 추가 여부를 묻는 메서드
    public boolean askToAddToBasket(Scanner sc, MenuItem item, Basket basket) {
        while (true) {
            System.out.printf("\n%s | W %.1f | %s\n",
                    item.getName(),
                    item.getPrice(),
                    item.getText());
            System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?\n");
            System.out.println("1. 확인         2. 취소");
            int l = input(sc);
            if (l == 1) {
                return addItemToBasket(item, basket);
            } else if (l == 2) {
                return false;
            } else {
                System.out.println("잘못된 번호를 입력했습니다.\n");
            }
        }
    }
    // 장바구니에 선택한 메뉴를 추가하는 메서드
    // 이미 장바구니에 선택한 메뉴가 있다면 수량을 +1
    public boolean addItemToBasket(MenuItem item, Basket basket) {
        boolean flag = true;
        for (int i = 0; i < basket.getBasketList().size(); i++) {
            if (basket.getOneBasket(i).getName().equals(item.getName())) {
                basket.getOneBasket(i).addCount();
                flag = false;
                break;
            }
        }
        if (flag) {
            basket.addBasket(new BasketItem(item.getName(), item.getPrice(), item.getText(), 1));
        }
        System.out.println(item.getName() + " 이 장바구니에 추가되었습니다.");
        return true;
    }
}
