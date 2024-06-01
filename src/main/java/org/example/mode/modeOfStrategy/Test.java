package org.example.mode.modeOfStrategy;

public class Test {

    public static void main(String[] args){

        Duck duck =new Duck();

        // 策略1
        duck.setQuackBehavior(new Quack1());
        duck.doQueck();

        // 策略2
        duck.setQuackBehavior(new Quack2());
        duck.doQueck();

    }
}
