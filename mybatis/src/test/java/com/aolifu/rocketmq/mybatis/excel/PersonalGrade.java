package com.aolifu.rocketmq.mybatis.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalGrade {

    @ExcelProperty({"名次"})
    private Integer ranking;

    @ExcelProperty({"名次"})
    private String name;

    @ExcelProperty({"名次"})
    private String teamName;
}
