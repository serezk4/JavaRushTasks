package com.javarush.task.task30.task3003;

import java.util.Objects;
import java.util.concurrent.TransferQueue;

public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.format("Элемент 'ShareItem-%d' добавлен%n", i);
            queue.offer(new ShareItem(("ShareItem-" + i), i));

            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }

            if (queue.hasWaitingConsumer())
                System.out.format("Consumer в ожидании!%n");
        }
    }

    @Override
    public String toString() {
        return "Producer{" +
                "queue=" + queue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(queue, producer.queue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queue);
    }
}
