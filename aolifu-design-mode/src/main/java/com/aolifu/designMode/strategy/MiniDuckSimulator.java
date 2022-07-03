package com.aolifu.designMode.strategy;

public class MiniDuckSimulator {
    
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.performQuack();
        duck.performFly();
    }
    
}
