package com.aolifu.mybatis.mapper;

import com.aolifu.mybatis.entity.EmpInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpInfoMapper {

    List<EmpInfo> queryAllEmpins();

}
