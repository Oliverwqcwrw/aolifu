package com.aolifu.cassandra.mapper;

import com.aolifu.cassandra.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRecordRepository extends CrudRepository<User, Integer> {
    
    List<User> findAllById(Integer id);
    
}
