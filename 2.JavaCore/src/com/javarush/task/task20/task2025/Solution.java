package com.javarush.task.task20.task2025;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Collections;
        import java.util.Date;
        import java.util.LinkedList;
        import java.util.List;
        import java.util.concurrent.Callable;
        import java.util.concurrent.ExecutionException;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;
        import java.util.concurrent.Future;

/*
Алгоритмы-числа
*/
public class Solution {

    private static final List<Long> results = new LinkedList<>();

    public static long[] getNumbers(long N) {
        long[] result = null;
        long timeStart = new Date().getTime();
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<List<Long>>> futures = new ArrayList<>();
        long start = 0;
        long end = 10;
        for (int i = 0; i < 10; i++) {
            start = end * i;
            end += (start + 1);
            futures.add(exec.submit(new Counting(start, end)));
        }
        long timeEnd = new Date().getTime();
        for(Future<List<Long>> fs : futures)
            try {
                // Вызов get() блокируется до завершения;:
                System.out.println(fs.get());
            } catch(InterruptedException e) {
                System.out.println(e);
                return null;
            } catch(ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
        System.out.println("Elapsed time (s): " + (timeEnd - timeStart) / 1000);
        return result;
    }

    private static class Counting implements Callable<List<Long>> {

        List<Long> list = new ArrayList<>();
        private long start;
        private long end;

        public Counting(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Long> call() {
            for (long i = start; i < end; i++) {
                char[] figs = String.valueOf(i).toCharArray();
                long res = 0;
                for (char fig : figs) {
                    res += Math.pow(Character.getNumericValue(fig), figs.length);
                }
                if (res == i) {
                    list.add(res);
                }
            }
            return list;
        }
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000000)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }
}
