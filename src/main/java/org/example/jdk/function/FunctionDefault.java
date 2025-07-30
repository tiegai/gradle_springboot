package org.example.jdk.function;

import org.example.jdk.function.inter.FunctionDefaultInterface;


public class FunctionDefault {

    public static void main(String[] args) {

        FunctionDefaultInterface functionDefaultInterface = () -> {
            System.out.println("死磕 Java 就是牛...");
        };

        // 调用抽象方法
        functionDefaultInterface.doSomething();

        // 调用默认方法
        functionDefaultInterface.defaultMethod("死磕 Netty 就是牛...");
        // 调用静态方法
        FunctionDefaultInterface.staticMethod("死磕 Java 并发就是牛...");


    }
}
