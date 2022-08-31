package com.aolifu.mybatis.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("test_user")
@Data
public class TestUser {

    @PrimaryKey
    private Integer id;

    private String username;

    private String password;
}
