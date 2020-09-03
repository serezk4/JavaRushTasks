package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        int radix = 10;
        String ss = s;

        if (!s.isEmpty() && s.length() >= 3) {
            if (s.substring(0, 2).equalsIgnoreCase("0x")) {
                radix = 16;
                ss = s.substring(2);
            } else if (s.substring(0, 2).equalsIgnoreCase("0b")) {
                radix = 2;
                ss = s.substring(2);
            } else if (s.charAt(0) == '0') {
                radix = 8;
                ss = s.substring(1);
            }
        }

        return String.valueOf(Integer.parseInt(ss, radix));
    }
}
