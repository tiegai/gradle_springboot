package org.example.jdk.optional;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;

import java.util.Optional;

@Slf4j
public class test {

    public static void main(String[] args) {

        log.info(Optional.of("创建非null对象，若对象为null则抛出异常").get());
        log.info(Optional.ofNullable("创建一个可能为null的对象，若为null则创建一个空对象").get());

        Optional<Integer> optional = Optional.of(10);
        log.info(String.valueOf(optional.isPresent()));


        class User {
            private String name;

            public User(String name) {
                this.name = name;
            }
        }

        User user = null;
        // orElse
        log.info(Optional.ofNullable(user).orElse(new User("xiaohong")).toString());
        // orElseGet
        log.info(Optional.ofNullable(user).orElseGet(
                () -> new User("xiaochen")
        ).toString());


        // orElseThrow
        try {
            Optional.ofNullable(user).orElseThrow(
                    () -> new RuntimeException("user is null")
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        // empty
        Optional<String> optionalEmpty = Optional.empty();
        if (optionalEmpty.isEmpty()) {
            System.out.println("optionalEmpty is empty");
        }

    }

}
