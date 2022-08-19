package com.aolifu.designMode.decorator;

import java.math.BigDecimal;

public class Mocha extends AbstractCondimentDecorator{
    
    AbstractBeverage beverage;
    
    public Mocha(AbstractBeverage beverage) {
        this.beverage = beverage;
    }
    
    public BigDecimal cost() {
        return new BigDecimal(0.2).add(beverage.cost());
    }
    
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }
}
