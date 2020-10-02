package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> result = new HashSet<>();
        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/"))
            pathToAnimals = pathToAnimals + "/";

        File dir = new File(pathToAnimals);
        String[] paths = dir.list((dir1, name) -> name.toLowerCase().endsWith(".class"));

        for (String p : paths) {
            try {
                boolean hasInterface = false;
                boolean hasConstructor = false;

                ClassLoader loader = new CustomClassLoader(pathToAnimals);

                String className = p.substring(0, p.length() - 6);
                Class clazz = loader.loadClass(className);

                Class[] interfaces = clazz.getInterfaces();
                for (Class i : interfaces) {
                    if (Animal.class == i) {
                        hasInterface = true;
                        break;
                    }
                }
                if (!hasInterface) continue;

                Constructor[] constructors = clazz.getConstructors();
                for (Constructor c : constructors) {
                    if (c.getParameterTypes().length == 0) {
                        hasConstructor = true;
                        break;
                    }
                }

                if (!hasConstructor) continue;

                result.add((Animal) clazz.newInstance());
            } catch (Exception e) {
                // ignore
            }
        }
        return result;
    }

    private static class CustomClassLoader extends ClassLoader {
        private String path;

        public CustomClassLoader(String path) {
            this.path = path;
        }

        @Override
        protected Class<?> findClass(String className) throws ClassNotFoundException {
            try {
                byte[] b = fetchClassFromFS(path + className + ".class");
                return defineClass(null, b, 0, b.length);
            } catch (IOException ex) {
                return super.findClass(className);
            }
        }

        private byte[] fetchClassFromFS(String path) throws IOException {
            InputStream is = new FileInputStream(new File(path));
            long length = new File(path).length();
            if (length > Integer.MAX_VALUE)
                return null;

            byte[] bytes = new byte[(int) length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
                offset += numRead;

            if (offset < bytes.length)
                throw new IOException("Could not completely read file " + path);

            is.close();
            return bytes;
        }
    }
}
