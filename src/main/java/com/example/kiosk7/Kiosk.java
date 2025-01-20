package com.example.kiosk7;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Kiosk {
    public Scanner sc = new Scanner(System.in);
    private final List <Menu> menuList;

    public Kiosk(List <Menu> menuList) {
        this.menuList = menuList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }
    public Menu getOneMenu(int index) {
        return menuList.get(index);
    }

    public void start() {
        Basket basket = new Basket();
        while (true) {
            // 카테고리를 보여주는 메인 메뉴 출력
            printMainMenu(basket);
            // 출력되는 줄 수를 파악하여 해당 값 보다 크면 예외 처리
            int max = getMenuList().size() + (basket.getBasketList().size() > 0 ? 2 : 0);
            int n = input();
            if (n == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (n > max || n < 0) {
                System.out.println("잘못된 번호를 입력했습니다.");
            } else if (n == getMenuList().size() + 1) {
                // 장바구니
                System.out.println("\n아래와 같이 주문 하시겠습니까?\n");
                handleBasket(basket);
            } else if (n == getMenuList().size() + 2) {
                // 장바구니 삭제
                basket.resetBasket();
                System.out.println("\n진행 중인 주문을 취소했습니다.");
            } else {
                // 상세 메뉴 출력 파트
                processDetailMenu (n , basket);
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

    // 세부화면 처리 메서드
    private void processDetailMenu(int n, Basket basket) {
        while (true) {
            Menu menu = getOneMenu(n - 1);
            printDetailMenu(menu);
            int m = input();
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
                boolean flag = askToAddToBasket(menuItem, basket);
                if (flag) {
                    break;
                }
            }
        }
    }

    // 사용자의 입력을 받는 메서드
    public int input() {
        while (true) {
            try {
                return sc.nextInt(); // 유효한 입력이면 바로 반환
            } catch (InputMismatchException e) {
                System.out.println("문자가 아닌 숫자를 입력해주세요.\n");
                sc.nextLine(); // 잘못된 입력을 버퍼에서 제거
            }
        }
    }

    // 장바구니 처리 메서드
    public void handleBasket(Basket basket) {
        while (true) {
            System.out.println("[ Orders ]");
            // 장바구니 출력 파트
            basket.printBasketItem();
            System.out.println("\n[ Total ]");
            System.out.println("W " + basket.totalPrice() + "\n");
            System.out.println("1. 주문  2. 메뉴 수정  3. 메뉴판");
            int num = input();
            if (num == 1) {
                int discountPercent = getUserDiscount(sc);
                double finalPrice = calculate(basket.totalPrice(), discountPercent);
                System.out.println("\n주문이 완료되었습니다. 금액은 W " + finalPrice + "입니다.\n");
                basket.resetBasket();
                break;
            } else if (num == 2) {
                // 장바구니에 특정 부분 삭제 파트
                System.out.println("삭제하실 메뉴의 번호를 눌러주세요.");
                basket.printBasketItemAndCount();
                System.out.println("0. 뒤로가기");
                int deleteNum = input();
                if (basket.getBasketList().size() < deleteNum || deleteNum < 0) {
                    System.out.println("잘못된 번호를 입력했습니다.\n");
                } else if (deleteNum == 0) {
                    break;
                } else {
                    basket.removeBasket(deleteNum);
                    break;
                }
            } else if (num == 3) {
                break;
            } else {
                System.out.println("잘못된 번호를 입력했습니다.\n");
            }
        }
    }

    // 장바구니 추가 여부를 묻는 메서드
    public boolean askToAddToBasket(MenuItem item, Basket basket) {
        while (true) {
            System.out.printf("\n%s | W %.1f | %s\n",
                    item.getName(),
                    item.getPrice(),
                    item.getText());
            System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?\n");
            System.out.println("1. 확인         2. 취소");
            int l = input();
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

    // 할인율을 얻어오는 메서드
    public int getUserDiscount (Scanner sc) {
        while (true) {
            System.out.println("\n할인 정보를 입력해주세요");
            System.out.println("1. 국가유공자 : 10% ");
            System.out.println("2. 군인      :  5%");
            System.out.println("3. 학생      :  3%");
            System.out.println("4. 일반      :  0%");
            int userNum = input();
            UserType userType = UserType.fromCode(userNum);
            if (userType == null) {
                System.out.println("잘못된 번호를 입력했습니다.");
                continue;
            }
            return userType.getDiscount();
        }
    }
    public double calculate(double totalPrice, int discountPercent) {
        return Math.round(totalPrice * (100 - discountPercent) / 100 * 100) / 100.0;
    }
}

