package com.aolifu.designMode.observer;

import java.math.BigDecimal;

public class WeatherStation {
    
    public static void main(String[] args) {
        WeatherDataSubject subject = new WeatherDataSubject();
        CurrentConditionDisplay display = new CurrentConditionDisplay(subject);
        BigDecimal num = new BigDecimal(10);
        subject.setMeasurements(num, num, num);
    }
    
}
