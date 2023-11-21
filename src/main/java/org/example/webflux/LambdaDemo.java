package org.example.webflux;

import java.util.stream.IntStream;

public class LambdaDemo {
    public static void main(String[] args) {
        int[] arr = {15, 24, 12, 451, 156};
        int min = Integer.MAX_VALUE;
        for (int a :
                arr) {
            if (a < min) {
                min = a;
            }
        }
        System.out.println(min);

        //jdk8 lambda，parallel()多线程处理
        int min2 = IntStream.of(arr).parallel().min().getAsInt();
        System.out.println(min2);
    }
}
