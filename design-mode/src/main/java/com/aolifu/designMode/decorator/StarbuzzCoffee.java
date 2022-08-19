package com.aolifu.designMode.decorator;

import java.math.BigDecimal;

public class StarbuzzCoffee {
    
    public static void main(String[] args) {
        AbstractBeverage beverage1 = new Espresso();
        System.out.println(beverage1.getDescription() + " $"  + beverage1.cost().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        
        AbstractBeverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        
        AbstractBeverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        
    }
    
}
