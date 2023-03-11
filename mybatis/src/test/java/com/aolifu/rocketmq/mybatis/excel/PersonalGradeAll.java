package com.aolifu.rocketmq.mybatis.excel;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalGradeAll {

    private String subGroup;

    private List<PersonalGrade> oneList;

    private List<PersonalGrade> twoList;

    private List<PersonalGrade> threeList;

    private List<PersonalGrade> fiveList;
}
