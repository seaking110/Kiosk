package com.example.kiosk7;

public enum UserType {
    NATIONAL_MERIT(10, 1),
    MILITARY(5, 2),
    STUDENT(3, 3),
    GENERAL(0, 4);
    private final int discount;

    private final int code;

    UserType(int discount, int code) {
        this.discount = discount;
        this.code = code;
    }

    // 할인율을 가져오는 메서드
    public int getDiscount() {
        return discount;
    }

    // code 값으로 해당 ENUM을 찾는 메서드
    public static UserType fromCode(int code) {
        for (UserType userType : values()) {
            if (userType.code == code) {
                return userType;
            }
        }
        return null; // 유효하지 않은 값일 경우 null 반환
    }
}
