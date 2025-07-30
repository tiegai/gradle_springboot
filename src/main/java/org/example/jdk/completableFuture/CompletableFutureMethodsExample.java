package org.example.jdk.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;

public class CompletableFutureMethodsExample {

    public static void main(String[] args) {
        // runAsync example
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Running async task");
        });

        // supplyAsync example
        CompletableFuture<String> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            return "Result from async task";
        });

        // thenApply example
        CompletableFuture<String> thenApplyFuture = supplyAsyncFuture.thenApply(result -> {
            return result + " - transformed";
        });

        System.out.println("主线先执行，CompletableFuture 等待执行完成再执行...");

        // thenAccept example
        thenApplyFuture.thenAccept(result -> {
            System.out.println("Result: " + result);
        });

        // thenCombine example
        CompletableFuture<String> combinedFuture = supplyAsyncFuture.thenCombine(thenApplyFuture, (result1, result2) -> {
            return result1 + " and " + result2;
        });

        // exceptionally example
        CompletableFuture<String> exceptionallyFuture = supplyAsyncFuture.exceptionally(ex -> {
            return "Exception occurred: " + ex.getMessage();
        });

        // whenComplete example
        supplyAsyncFuture.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Completed successfully with result: " + result);
            } else {
                System.out.println("Completed with exception: " + ex.getMessage());
            }
        });

        // handle example
        supplyAsyncFuture.handle((result, ex) -> {
            if (ex == null) {
                return "Handled result: " + result;
            } else {
                return "Handled exception: " + ex.getMessage();
            }
        });

        // allOf example
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(runAsyncFuture, supplyAsyncFuture);

        // anyOf example
        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(runAsyncFuture, supplyAsyncFuture);

        // Wait for all futures to complete
        try {
            allOfFuture.get();
            System.out.println("All tasks completed");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}