package org.example.jdk.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class RunAsync {

    // runAsync()： 用于异步执行没有返回值的任务
    public static void main(String[] args) {

        // 会使用 ForkJoinPool 作为它的线程池执行异步代码
        CompletableFuture.runAsync(() -> {
            System.out.println("死磕 Java 就是牛...");
        });

        // 会使用指定的线程池执行异步代码
        CompletableFuture.runAsync(() -> {
            System.out.println("死磕 Netty 就是牛...");
        }, Executors.newFixedThreadPool(2));

        CompletableFuture.runAsync(() ->{
            System.out.println("CompletableFuture 任务开始执行...");
            for (int i = 0; i < 100 ; i++) {
                System.out.println("CompletableFuture 任务执行中[{}]..." + i);
            }
            System.out.println("CompletableFuture 任务执行完毕...");
        });

        System.out.println("主线程执行完毕...");


        // get() join() 方法会阻塞当前线程直到异步任务执行完毕
        // get() 会抛出 InterruptedException 和 ExecutionException 这两个受检查异常
        // join() 会抛出 CompletionException 这个运行时异常
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("死磕 Java 就是牛");

        System.out.println(completableFuture.join());

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            // 捕获异常并处理
            // 或者直接抛出
        }

    }
}
