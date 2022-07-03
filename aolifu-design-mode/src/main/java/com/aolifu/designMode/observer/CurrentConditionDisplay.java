package com.aolifu.designMode.observer;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

public class CurrentConditionDisplay implements Observer, DisplayElement {
    
    Observable observable;
    private BigDecimal temperature;
    private BigDecimal humidity;
    
    public CurrentConditionDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }
    
    public void update(Observable observable, Object arg) {
        if (observable instanceof WeatherDataSubject) {
            WeatherDataSubject subject = (WeatherDataSubject) observable;
            this.temperature = subject.getTemperature();
            this.humidity = subject.getHumidity();
            this.display();
        }
    }
    
    public void display() {
        System.out.println("Temperature: " + this.temperature + " Humidity: " + this.humidity);
    }
}
