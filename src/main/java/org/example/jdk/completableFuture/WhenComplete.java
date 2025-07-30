package org.example.jdk.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class WhenComplete {

    public static void main(String[] args) {

        var completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("死磕 Java 新特性 - 02222222222");
            return "死磕 Java 新特性 - 01111111";
        });

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("[completableFuture-1] - www.skjava.com 网站就是牛..");
            return "[completableFuture-1] - 死磕 Java 新特性";
        }).whenComplete((res,ex) -> {
            if (ex == null) {
                System.out.println("结果是:" + res);
            } else {
                System.out.println("发生了异常，异常信息是:" + ex.getMessage());
            }
        });

        CompletableFuture<String> future = CompletableFuture.completedFuture("死磕 Netty");
        future.thenRun(()  ->{
            System.out.println("CompletableFuture 计算执行完成，开始执行后续操作...");
        });

        Stream<String> stream = Stream.of("死磕 Java","死磕 Java 并发","死磕 Java 新特性","死磕 Netty");
        stream.map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("================");

        // flatMap() 通常用于将嵌套的集合结构扁平化，或者将元素进行扁平映射以进行处理
        List<List<String>> list = Arrays.asList(
                Arrays.asList("死磕 Java","死磕 Java 并发"),
                Arrays.asList("死磕 Java 基础"),
                Arrays.asList("死磕 Java NIO","死磕 Netty"),
                Arrays.asList("死磕 Redis","死磕 Spring"),
                Arrays.asList("死磕 Java 新特性")
        );

        list.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);


        var list1 = List.of("死磕 Java 新特性","死磕 Java 并发","死磕 Netty");
        var copyList1 = List.copyOf(list1);
        System.out.println(list1 == copyList1);

        var list2 = Arrays.asList("死磕 Java 新特性","死磕 Java 并发","死磕 Netty");
        var copyList2 = List.copyOf(list2);
        System.out.println(list2 == copyList2);




    }



}
