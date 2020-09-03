package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        int[] values = {1, 3, 9, 27, 81, 243, 729, 2187};
        StringBuilder res = new StringBuilder();

        int nn = number;
        while (nn > 0) {
            if (nn % 3 == 0) {
                res.append("0");
                nn /= 3;
            } else if (nn % 3 == 1) {
                res.append("+");
                nn /= 3;
            } else {
                res.append("-");
                nn /= 3;
                nn++;
            }
        }

        int sum = 0;
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) == '+') {
                str.append(" + ").append(values[i]);
                sum += values[i];
            } else if (res.charAt(i) == '-') {
                str.append(" - ").append(values[i]);
                sum -= values[i];
            }
        }
        System.out.println(sum + " =" + str);
    }
}