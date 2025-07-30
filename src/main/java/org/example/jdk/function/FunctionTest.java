package org.example.jdk.function;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
// 函数式接口
public class FunctionTest {

    // 类型转换
    Function<String, Integer> function = (str) -> {
        return str.length();
    };

    // List<String>是入参类型，String是返回值类型
    // 数据处理
    Function<List<String>, String> stringFunction = (list) -> {
        StringBuilder sb = new StringBuilder();
        list.forEach(str -> sb.append(str).append(" "));
        return sb.toString();
    };

    //andThen()：方法链式调用

    Function<String,Integer> function1 = t-> Integer.parseInt(t);
    Function<Integer,Integer> function2 = t-> t*2;

    // compose()：方法链式调用
    Function<String,Integer> functionA = t -> {
        log.info("begin functionA");
        return Integer.parseInt(t);
    };
    Function<Integer,Integer> functionB = t -> {
        log.info("begin functionB");
        return t * 10;
    };
    Function<Integer,String> functionC = t -> {
        log.info("begin functionC");
        return t.toString();
    };

    // identity() 返回一个恒等函数，它仅返回其输入值，对输入值不进行任何操作
    Function<String,String> functionAA = Function.identity();
    Function<String,String> functionBB = str -> str.toUpperCase();
    Function<String,String> functionCC = str -> str + " WORLD!!!";

    // Supplier 是一个代表生产（或供应）某种结果的接口，它不接受任何参数，但能够提供一个结果
    Supplier<LocalDate> supplier = () -> LocalDate.now();

    // Predicate 表示一个谓词，它接受一个输入参数并返回一个布尔值，用于表示某个条件是否满足
    Predicate<String> predicate = (str) -> str.length() > 5;
    // and()：表示两个 Predicate 的 与操作
    Predicate<Integer> predicate1 = x -> x > 10;
    Predicate<Integer> predicate2 = x -> x % 2 == 0;
    boolean result1 = predicate1.and(predicate2).test(13);
    // or()：表示两个 Predicate 的或操作
    Predicate<Integer> predicate11 = x -> x > 10;
    Predicate<Integer> predicate22 = x -> x % 2 == 0;
    boolean result2 = predicate11.or(predicate22).test(13);
    // negate()：表示 Predicate 的逻辑非操作
    Predicate<Integer> predicate111 = x -> x > 10;


    public static void main(String[] args) {
        FunctionTest functionTest = new FunctionTest();
        // 类型转换
        log.info(functionTest.function.apply("死磕 Java 新特性").toString());
        // 数据处理
        log.info(functionTest.stringFunction.apply(List.of("死磕", "Java", "新特性")));
        //andThen()：方法链式调用 ,先执行function1,将20转换为Integer,再执行function2,将20*2
        log.info(functionTest.function1.andThen(functionTest.function2).apply("20").toString());
        // compose()：方法链式调用,先执行functionA,将20转换为Integer,再执行functionB,将20*10,再执行functionC,将200转换为String
        log.info(functionTest.functionC.compose(functionTest.functionB).compose(functionTest.functionA).apply("20"));
        // identity()
        log.info(functionTest.functionCC.compose(functionTest.functionAA.andThen(functionTest.functionBB)).apply("hello"));
        // Supplier
        log.info(functionTest.supplier.get().toString());
        // Predicate
        log.info(String.valueOf(functionTest.predicate.test("判断字符串长度是否符合要求")));
        // and()：表示两个 Predicate 的 与操作
        log.info(String.valueOf(functionTest.result1));
        // or()：表示两个 Predicate 的或操作
        log.info(String.valueOf(functionTest.result2));
        // negate()：表示 Predicate 的逻辑非操作
        log.info(functionTest.predicate111.negate().test(13) + "");

    }


}
