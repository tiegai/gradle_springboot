package org.example.jdk.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class SupplyAsync {

    public static void main(String[] args) {

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() ->{
            System.out.println("死磕 Java 新特性 - 01");
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "死磕 Java 新特性 - 01111111";
        });

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() ->{
            System.out.println("死磕 Java 新特性 - 02");
            return "死磕 Java 新特性 - 0222222";
        }, Executors.newFixedThreadPool(10));

        System.out.println(completableFuture1.join());
        System.out.println(completableFuture2.join());




    }
}
