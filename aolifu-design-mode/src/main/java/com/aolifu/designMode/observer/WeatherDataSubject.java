package com.aolifu.designMode.observer;

import java.math.BigDecimal;
import java.util.Observable;

public class WeatherDataSubject extends Observable {
    
    private BigDecimal temperature;
    
    private BigDecimal humidity;
    
    private BigDecimal pressure;
    
    public WeatherDataSubject(){}
    
    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }
    
    public void  setMeasurements(BigDecimal temperature, BigDecimal humidity, BigDecimal pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
    
    public BigDecimal getTemperature() {
        return temperature;
    }
    
    public BigDecimal getHumidity() {
        return humidity;
    }
    
    public BigDecimal getPressure() {
        return pressure;
    }
    
}
