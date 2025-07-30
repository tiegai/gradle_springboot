package org.example.jdk.function.inter;


// 函数型接口
@FunctionalInterface
public interface FunctionDefaultInterface {

    /**
     * 抽象方法
     */
    void doSomething();

    /**
     * 默认方法
     * @param s
     */
    default void defaultMethod(String s) {
        System.out.println("默认方法：" + s);
    }

    /**
     * 静态方法
     * @param s
     */
    static void staticMethod(String s) {
        System.out.println("静态方法：" + s);
    }

}
