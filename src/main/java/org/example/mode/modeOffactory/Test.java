package org.example.mode.modeOffactory;

public class Test {

    public static int calculateUsingFactory(int a, int b, String operator) {
        Operation targetOperation = OperatorFactory
                .getOperation(operator)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
        return targetOperation.apply(a,b);

    }

    public static void main(String[] args){
        // add
        System.out.println(Test.calculateUsingFactory(5,2, "add"));
        //divide
        System.out.println(Test.calculateUsingFactory(5,2, "divide"));
        //error
        //System.out.println(UsingFactory.calculateUsingFactory(5,2, "error"));
    }

}
