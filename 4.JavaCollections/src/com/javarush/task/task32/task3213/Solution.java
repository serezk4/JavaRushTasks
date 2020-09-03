package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null)
            return "";
        StringWriter strW = new StringWriter();

        char[] buff = new char[1024 * 1024];
        int len;
        while ((len = reader.read(buff)) != -1) {
            for (int i = 0; i < len; i++)
                buff[i] = (char) (buff[i] + key);
            strW.write(buff, 0, len);
        }

        return strW.toString();
    }
}
