package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        String[] letters = "qwertyuiopasdfghjklzxcvbnm".split("");
        String[] numbers = "1234567890".split("");
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < 6; i++)
            pass.append(letters[(int) (Math.random() * (letters.length - 1))]);
        pass.append(letters[(int) (Math.random() * (letters.length - 1))].toUpperCase());
        pass.append(numbers[(int) (Math.random() * (numbers.length - 1))]);


        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            byteOut.write(pass.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteOut;
    }
}