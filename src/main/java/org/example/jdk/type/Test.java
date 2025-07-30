package org.example.jdk.type;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

    public static void main(String[] args) {

        // @NonNull
        Object obj = "skjava.com";
        String str = (@NonNull String) obj;
        log.info(str);


        // @ReadOnly 只读
        /**
         * class CustomList implements @ReadOnly List<String> {
         *     // ...
         * }
         */

        // @Positive 注解表示这个方法应该返回一个正数
        /**
         * public @Positive int getPositiveNumber() {
         *     return 42;
         * }
         */


        //@Critical 注解被用于标记抛出的 IOException 是关键的，可能需要特别处理
        /**
         * public void readData() throws @Critical IOException {
         *     // ...
         * }
         */


    }
}
