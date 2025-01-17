# 키오스크 프로그램

## 개요

이 프로젝트는 콘솔 기반 키오스크 프로그램으로, 사용자가 메뉴를 탐색하고 장바구니를 관리하며, 할인 정보를 입력하여 최종 결제까지 진행할 수 있는 시스템입니다. Java로 개발되었으며, 객체지향적인 설계를 통해 유지보수성과 확장성을 고려했으며, 이 프로젝트는 kiosk7 패키지를 최종으로 점진적으로 기능을 확장하며 개발되었습니다.

---

## 주요 기능

### 1. **Enum을 활용한 할인 처리**
- **할인 적용**: 국가유공자, 군인, 학생 등 사용자 유형별로 다른 할인율이 적용됩니다.
- Enum을 활용하여 할인율을 관리함으로써 유지보수성과 가독성을 높였습니다.
- 다음은 `UserType` Enum 클래스의 일부 코드입니다:

```java
public enum UserType {
    NATIONAL_MERIT(10, 1),
    MILITARY(5, 2),
    STUDENT(3, 3),
    GENERAL(0, 4);

    private final int code;
    private final int discount;

    UserType(int discount, int code) {
        this.discount = discount;
        this.code = code;
    }

    public int getDiscount() {
        return discount;
    }

    public static UserType fromCode(int code) {
        for (UserType userType : values()) {
            if (userType.code == code) {
                return userType;
            }
        }
        return null;
    }
}
```

### 2. **장바구니 관리**
- 장바구니에 아이템을 추가하고, 삭제하며, 총 금액을 계산할 수 있는 기능이 포함되어 있습니다.
- 장바구니 관리와 관련된 주요 메서드는 `Basket` 클래스에 정의되어 있습니다.

#### 예시: 장바구니에 아이템 추가하기
```java
public boolean addItemToBasket(MenuItem item, Basket basket) {
    for (BasketItem basketItem : basket.getBasketList()) {
        if (basketItem.getName().equals(item.getName())) {
            basketItem.addCount();
            return true;
        }
    }
    basket.addBasket(new BasketItem(item.getName(), item.getPrice(), item.getText(), 1));
    return true;
}
```

#### 예시: 장바구니 총 가격 계산하기
```java
public double totalPrice() {
    double sum = 0;
    for (BasketItem item : basketList) {
        sum += item.getPrice() * item.getCount();
    }
    return Math.round(sum * 10) / 10.0; // 소수점 첫째 자리까지 반올림
}
```
#### 예시 : 장바구니에서 특정 물품 삭제하기
```java
  public void removeBasket(int index) {
    List<BasketItem> updatedList = getBasketList().stream()
      .filter(item -> getBasketList().indexOf(item) != index - 1) // index 조건 제외
      .toList();
    getBasketList().clear(); // 원본 리스트를 비움
    getBasketList().addAll(updatedList); // 새 리스트를 추가
  }
```

---


## 사용 예시

### 프로그램 실행 후 단계별 흐름:

1. **메인 메뉴 출력**
   - 카테고리를 선택하거나, 장바구니를 확인하거나, 종료를 선택할 수 있습니다.
   
   ```
   [ MAIN MENU ]
   1. Burgers
   2. Drinks
   3. Desserts
   0. 종료     | 종료 
   
   [ ORDER MENU ]
   4. Orders | 장바구니를 확인 후 주문합니다.
   5. Cancel | 진행 중인 주문을 취소합니다.
   ```

2. **상세 메뉴 확인 및 장바구니 추가**
   - 특정 카테고리를 선택하면 세부 메뉴가 출력됩니다.
   - 메뉴를 선택하여 장바구니에 추가합니다.

   ```
   [ Burgers ]
   1. Thigh Burger         | W 4.9 | 치킨 패티와 양상추가 조화를 이루는 시그니처 버거.
   2. Fire Thigh Burger    | W 5.1 | 불맛이 살아있는 버거, 싸이버거의 매운맛 버전.
   3. White Garlic Burger  | W 5.5 | 화이트 갈릭소스와 더블햄 통가슴살 패티까지 담은 버거.
   4. Deep Cheese Burger   | W 5.4 | 부드러운 치즈와 통가슴살 패티 버거.
   0. 뒤로가기
   ```

3. **장바구니 확인 및 주문**
   - 장바구니에 추가된 항목을 검토하고, 할인 정보를 입력 후 결제를 진행합니다.

   ```
   [ Orders ]
   Thigh Burger         | W 4.9 | 치킨 패티와 양상추가 조화를 이루는 시그니처 버거. X 1

   [ Total ]
   W 4.9
   
   할인 정보를 입력해주세요:
   1. 국가유공자: 10%
   2. 군인      : 5%
   3. 학생      : 3%
   4. 일반      : 0%
   ```

4. **최종 결제 금액 출력**
   - 할인율이 적용된 최종 금액을 출력합니다.

   ```
   주문이 완료되었습니다. 금액은 W 4.66입니다.
   ```

---

## 향후 추가할 기능

    **관리자 모드**
   - 새로운 메뉴 추가 및 삭제
   - 기존 메뉴의 가격 수정
   - 주문 내역 조회 및 관리



---

이 프로젝트는 향후 추가 기능과 개선 사항을 통해 더욱 완성도 높은 키오스크 프로그램으로 발전시킬 예정입니다.

