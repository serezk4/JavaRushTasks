package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        try {
            String input = args[0];

            for (int i = 2; i <= 36; i++) {
                try {
                    new BigInteger(input, i);
                    System.out.println(i);
                    return;
                } catch (NumberFormatException ignored) {
                }
            }

            System.out.println("incorrect");
        } catch (Exception ignored) {}

    }
}