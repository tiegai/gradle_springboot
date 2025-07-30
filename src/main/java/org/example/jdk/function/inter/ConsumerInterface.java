package org.example.jdk.function.inter;

@FunctionalInterface
// 消费型接口
public interface ConsumerInterface<T> {

    void accept(T t);

    default ConsumerInterface<T> andThen(ConsumerInterface<? super T> after) {
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }

}
