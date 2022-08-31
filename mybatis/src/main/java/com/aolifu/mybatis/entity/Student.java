package com.aolifu.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = 237497405888248762L;

    @Id// 必须指定id列
    private Long studentId;

    private String studentName;

    private Integer studentAge;

    private Double studentScore;
    
    private Date studentBirthday;
}


