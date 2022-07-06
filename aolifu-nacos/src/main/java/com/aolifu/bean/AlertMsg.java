package com.aolifu.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class AlertMsg implements Serializable {
    
    private static final long serialVersionUID = 5461692409127900350L;
    
    private Integer scopeId;
    
    private String scope;
    
    private String name;
    
    private String alarmMessage;
    
    

}
