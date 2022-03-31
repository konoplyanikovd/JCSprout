package com.konoplyanikovd.interview.algorithm;

import java.util.HashSet;
import java.util.Set;

public class HappyNum {

    public boolean isHappy(int number) {
        Set<Integer> set = new HashSet<>(30);
        while (number != 1) {
            int sum = 0;
            while (number > 0) {
                sum += (number % 10) * (number % 10);
                number = number / 10;
            }
            if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
            }
            number = sum;
        }
        return true;
    }


    public static void main(String[] args) {
        int num = 345;
        int i = num % 10;
        int i1 = num / 10;
        int i2 = i1 / 10;
        System.out.println(i);
        System.out.println(i1);
        System.out.println(i2);
    }
}
