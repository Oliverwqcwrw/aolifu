package com.aolifu.designMode.decorator;

import java.math.BigDecimal;

public class HouseBlend extends AbstractBeverage{
    
    public HouseBlend() {
        description = "House Blend Coffee";
    }
    
    public BigDecimal cost() {
        return new BigDecimal(0.89);
    }
}
