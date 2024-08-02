package com.github.javarar.rejected.task;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomThreadExecutors {

    private static final AtomicInteger rejectedCount = new AtomicInteger();
    private static final AtomicInteger successCount = new AtomicInteger();
    public static Executor logRejectedThreadPoolExecutor(Integer corePoolSize, Integer maxPoolSize, Long keepAliveTime, Integer queueSize)
    {
//        throw new UnsupportedOperationException("реализуй меня");
        return new ThreadPoolExecutor(
                corePoolSize, maxPoolSize,
                keepAliveTime, TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue<>(queueSize),
                runnable -> {
                    System.out.println("Execute thread " + successCount.incrementAndGet());
                    return new Thread(runnable);
                },
                (runnable, executor) -> {
                    System.out.println("Task rejected " + rejectedCount.incrementAndGet());
                }
        );
    }

    public static int getRejectedCount() {
        return rejectedCount.get();
    }
    public static int getSuccessCount() {
        return successCount.get();
    }
}
