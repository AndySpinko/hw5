package com.github.javarar.rejected.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;

class CustomTreadExecutorsTest {

    private final Executor executor =
            CustomThreadExecutors.logRejectedThreadPoolExecutor(3, 3, 2000L, 3);

    @Test
    public void threadPoolDoesNotThrowExceptionOnQueueOverflow() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        Assertions.assertEquals(4, CustomThreadExecutors.getRejectedCount());
        Assertions.assertEquals(3, CustomThreadExecutors.getSuccessCount());
    }
}
