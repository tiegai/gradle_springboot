package org.example.mode.modeOfStrategy;

public class Duck {

    private QuackBehavior quackBehavior;

    public void doQueck(){
        if(quackBehavior != null){
            quackBehavior.quack();
        }
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

}
