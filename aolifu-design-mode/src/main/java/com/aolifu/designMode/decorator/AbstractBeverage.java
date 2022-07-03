package com.aolifu.designMode.decorator;

import java.math.BigDecimal;

public abstract class AbstractBeverage {
    
    String description = "Unknown Beverage";
    
    public String getDescription() {
        return description;
    }
    
    public abstract BigDecimal cost();
    
}
