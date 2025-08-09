package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;

public class Validation {
    public static boolean isBurgerValid(Burger burger) {
        if (burger == null) {
            return false;
        }

//        if (burger.getName() == null || burger.getName().isBlank()) {
//            return false;
//        }
//
//        if (burger.getPrice() < 0) {
//            return false;
//        }
//
//        if (burger.getContents() == null || burger.getContents().isBlank()) {
//            return false;
//        }
//
//        if (burger.getBreadType() == null) {
//            return false;
//        }

        return true;
    }

    public static boolean isIdValid(long id) {
        return id > 0 && id < 500;
    }

    public static boolean isPriceValid(double price) {
        return price > 0;
    }

    public static boolean isBreadTypeValid(BreadType breadType) {
        for (BreadType type : BreadType.values()) {
            if (breadType == type) {
                return true;
            }
        }
        return false;
    }

    public static boolean isContentValid(String content) {
        return content != null || !content.isBlank();
    }
}
