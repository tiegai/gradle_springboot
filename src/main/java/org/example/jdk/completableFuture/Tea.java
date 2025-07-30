package org.example.jdk.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Tea {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future1  = CompletableFuture.supplyAsync(()->{
            System.out.println("1");
            return "11";
        });

        CompletableFuture<String> future2  = CompletableFuture.supplyAsync(() -> {
            System.out.println("2");
            return "22";
        });

        CompletableFuture<String> future3  = CompletableFuture.supplyAsync(() ->{
            System.out.println("3");
            return "33";
        });

        CompletableFuture<String> future4  = CompletableFuture.supplyAsync(() -> {
            System.out.println("4");
            return "44";
        });

        CompletableFuture<String> future11 = future1.thenApply((result) -> {
            System.out.println("拿到" + result + ",开始洗" + result);
            return "干净的开水壶";
        });

        CompletableFuture<String> future12 = future11.thenApply((result) -> {
            System.out.println("拿到"  + result + ",开始烧开水");
            return "12";
        });

        CompletableFuture<String> future21 = future2.thenApply((result) -> {
            System.out.println("拿到" + result + ",开始洗" + result);
            return "21";
        });

        CompletableFuture<String> future31 = future3.thenApply((result) -> {
            System.out.println("拿到" + result + ",开始洗" + result);
            return "31";
        });


        CompletableFuture<Void> future5  = CompletableFuture.allOf(future4,future12,future21,future31);
        future5.thenRun(() -> {
            System.out.println("泡好了茶，还是喝美味的西湖龙井茶");
        });

        TimeUnit.SECONDS.sleep(5);
    }
}
