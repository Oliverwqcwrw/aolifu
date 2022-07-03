package com.aolifu.designMode.decorator;

import java.math.BigDecimal;

public class Soy extends AbstractCondimentDecorator{
    
    private AbstractBeverage beverage;
    
    public Soy(AbstractBeverage beverage) {
        this.beverage = beverage;
    }
    
    public BigDecimal cost() {
        return new BigDecimal(1.3);
    }
    
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }
}
