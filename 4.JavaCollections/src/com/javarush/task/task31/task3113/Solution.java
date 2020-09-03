package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        Path filePath = Paths.get(consoleReader.readLine());

        if (Files.isRegularFile(filePath))
            System.out.printf("%s - не папка%n", filePath.toString());

        AtomicLong allFolders = new AtomicLong(0);
        AtomicLong allFiles = new AtomicLong(0);
        AtomicLong size = new AtomicLong(0);

        Files.walkFileTree(filePath, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                allFolders.addAndGet(1);
                size.addAndGet(attrs.size());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                allFiles.addAndGet(1);
                size.addAndGet(attrs.size());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });



        System.out.printf("Всего папок - %s%nВсего файлов - %s%nОбщий размер - %s%n", allFolders.get() - 1, allFiles.get(), size.get());
    }
}
