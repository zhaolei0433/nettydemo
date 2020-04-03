package com.example.mongdbdemo.dao;

import com.example.mongdbdemo.entity.SecondEntity;
import com.example.mongdbdemo.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhaolei
 * Create: 2019/1/28 11:39
 * Modified By:
 * Description:
 */
@Repository
public interface secondDao extends MongoRepository<SecondEntity, String> {
    List<SecondEntity> findAll();
}
