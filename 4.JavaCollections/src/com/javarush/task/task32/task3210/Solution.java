package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) throws IOException {
        String input = args[2];
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        byte[] fileContent = new byte[input.length()];

        raf.seek(Integer.parseInt(args[1]));
        raf.read(fileContent, 0, input.length());

        String textFromByteArray = new String(fileContent);
        raf.seek(raf.length());
        if (textFromByteArray.equals(input))
            raf.write("true".getBytes());
        else
            raf.write("false".getBytes());

    }
}
