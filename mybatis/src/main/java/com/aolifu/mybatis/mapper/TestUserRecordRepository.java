package com.aolifu.mybatis.mapper;

import com.aolifu.mybatis.entity.TestUser;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TestUserRecordRepository extends CrudRepository<TestUser, Integer> {
    
    List<TestUser> findAllById(Integer id);
    
}
