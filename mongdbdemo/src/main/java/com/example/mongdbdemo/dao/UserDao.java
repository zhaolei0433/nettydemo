package com.example.mongdbdemo.dao;

import com.example.mongdbdemo.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
@Repository
public interface UserDao  extends MongoRepository<UserEntity, String> {
    List<UserEntity> findAll();

    List<UserEntity> findByMsn(String msn);
}
