package com.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.api.model.UserEntity;

public interface UserSecurityRepository extends CrudRepository <UserEntity, String>{
    
}
