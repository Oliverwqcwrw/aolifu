package com.aolifu.designMode.strategy;

public class MuteQuack implements QuackBehavior{
    
    public void quack() {
        System.out.println("Slience");
    }
}
