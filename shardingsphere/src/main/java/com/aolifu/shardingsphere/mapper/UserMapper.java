package com.aolifu.shardingsphere.mapper;

import com.aolifu.shardingsphere.entity.CreateTableSql;
import com.aolifu.shardingsphere.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface UserMapper extends BaseMapper<User> {
     /**
      * 查询数据库中的所有表名
      *
      * @param schema 数据库名
      * @return 表名列表
      */
     List<String> getAllTableNameBySchema(@Param("schema") String schema);

     /**
      * 查询建表语句
      *
      * @param tableName 表名
      * @return 建表语句
      */
     CreateTableSql SelectTableCreateSql(@Param("tableName") String tableName);

     /**
      * 执行SQL
      *
      * @param sql 待执行SQL
      */
     void executeSql(@Param("sql") String sql);

}
