package org.example.mode.modeOfenum;

public class Test {

    public static int calculate(int a, int b, Operator operator) {
        return operator.apply(a, b);
    }

    public static void main(String[] args){

        System.out.println(Test.calculate(1,3, Operator.valueOf("ADD")));

        System.out.println(Test.calculate(6,3, Operator.valueOf("DIVIDE")));

    }
}
