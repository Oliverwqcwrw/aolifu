package com.aolifu.designMode.decorator;

import java.math.BigDecimal;

public class DarkRoast extends AbstractBeverage{
    
    public DarkRoast() {
        description = "DarkRoast";
    }
    
    public BigDecimal cost() {
        return new BigDecimal(2.1);
    }
}
