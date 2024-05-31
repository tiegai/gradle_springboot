package org.example.mode.modeOfcommand;

public class Test {

    public static int calculate(Command command){

        return command.execute();

    }

    public static void main(String[] args){

        System.out.println(Test.calculate(new AddCommand(1,2)));

        System.out.println(Test.calculate(new DivideCommand(4,1)));

    }
}
