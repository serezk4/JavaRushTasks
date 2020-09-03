package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File newFile = new File(resultFileAbsolutePath.getParent() + "/" + "allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, newFile);

        try (FileOutputStream fileWriter = new FileOutputStream(newFile)) {
            List<File> resultList = new ArrayList<>();

            Files.walkFileTree(path.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                    if (Files.isRegularFile(path)) {
                        if (basicFileAttributes.size() <= 50) {
                            resultList.add(new File(path.toString()));
                        } else FileUtils.deleteFile(new File(path.toString()));
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path path, IOException e) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path path, IOException e) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });

            resultList.sort(Comparator.comparing(File::getName));

            for (File paths : resultList){
                FileInputStream fileReader = new FileInputStream(paths);
                while (fileReader.available() > 0)
                    fileWriter.write(fileReader.read());
                fileWriter.write(System.lineSeparator().getBytes());
                fileReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
