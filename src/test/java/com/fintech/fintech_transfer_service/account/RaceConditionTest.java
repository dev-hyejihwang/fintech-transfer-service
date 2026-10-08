package com.fintech.fintech_transfer_service.account;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaceConditionTest {

    @Test
    void reproduceLostUpdate() throws Exception {
        int balance = 100;

        AtomicInteger sharedBalance = new AtomicInteger(balance);

        CountDownLatch ready = new CountDownLatch(2);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch bothRead = new CountDownLatch(2);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            Runnable deposit = () -> {
                ready.countDown();

                try {
                    start.await();

                    int oldBalance = sharedBalance.get();

                    bothRead.countDown();
                    bothRead.await();

                    sharedBalance.set(oldBalance + 10);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            };

            Future<?> taskA = executor.submit(deposit);
            Future<?> taskB = executor.submit(deposit);

            ready.await();
            start.countDown();

            taskA.get();
            taskB.get();

            assertEquals(110, sharedBalance.get());

        } finally {
            executor.shutdownNow();
        }
    }


}
