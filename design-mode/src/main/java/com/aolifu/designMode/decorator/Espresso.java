package com.aolifu.designMode.decorator;

import java.math.BigDecimal;

public class Espresso extends AbstractBeverage{
    
    public Espresso() {
        description = "Espresso";
    }
    
    public BigDecimal cost() {
        return new BigDecimal(1.99);
    }
}
