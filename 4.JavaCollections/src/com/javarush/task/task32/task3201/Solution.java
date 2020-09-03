package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        if (args.length < 3)
            return;

        long filePos = Long.parseLong(args[1]);
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++)
            sb.append(args[i]).append(" ");

        try(RandomAccessFile raf = new RandomAccessFile(args[0], "rw")) {
            if (filePos > raf.length())
                raf.seek(raf.length());
            else if (filePos < 0)
                raf.seek(0);
            else
                raf.seek(filePos);
            raf.write(sb.toString().trim().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
