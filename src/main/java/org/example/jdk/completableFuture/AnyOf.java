package org.example.jdk.completableFuture;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class AnyOf {

    @SneakyThrows
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() ->{
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("死磕 Netty 执行完成...");
            return "死磕 Netty";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() ->{
            try {
                sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("死磕 Java 并发 执行完成...");
            return "死磕 Java 并发";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() ->{
            try {
                sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("死磕 Redis 执行完成...");
            return "死磕 Redis";
        });

        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() ->{
            try {
                sleep(4);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("死磕 Java 新特性 执行完成...");
            return "死磕 Java 新特性";
        });

        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() ->{
            try {
                sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("死磕 Spring 执行完成...");
            return "死磕 Spring";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1,future2,future3,future4,future5);
        anyOfFuture.thenAccept(result -> {
            System.out.println("接收到的结果为:" + result);
        });

        sleep(10);
    }
}
