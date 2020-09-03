package com.javarush.task.task30.task3003;

import java.util.Objects;
import java.util.concurrent.TransferQueue;

public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(450);
        } catch (InterruptedException ignored) {
        }

        while (true) {
            try {
                queue.take();
                System.out.format("Processing item.toString()%n");
            } catch (InterruptedException ignored) {
            }



        }
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "queue=" + queue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(queue, consumer.queue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queue);
    }
}
