package com.aolifu.designMode.decorator;

import java.math.BigDecimal;

public class Whip extends AbstractCondimentDecorator{
    
    AbstractBeverage  beverage;
    
    public Whip(AbstractBeverage beverage) {
        this.beverage = beverage;
    }
    
    public BigDecimal cost() {
        return new BigDecimal(1.1).add(beverage.cost());
    }
    
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }
}
