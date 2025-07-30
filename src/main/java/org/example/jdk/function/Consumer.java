package org.example.jdk.function;

import org.example.jdk.function.inter.ConsumerInterface;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Consumer {

    public static void main(String[] args) {

        ConsumerInterface<String> consumer = (s) -> {
            System.out.println("死磕 Java 就是牛..." + s);
        };

        consumer.accept("死磕 Netty 就是牛...");


    }

}
