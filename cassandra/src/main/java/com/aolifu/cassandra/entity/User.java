package com.aolifu.cassandra.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("user")
@Data
public class User {

    @PrimaryKey
    private Integer id;

    private String username;

    private String password;
}
