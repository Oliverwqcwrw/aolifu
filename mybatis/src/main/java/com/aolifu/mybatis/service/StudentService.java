package com.aolifu.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertStudent() {

    }
}
