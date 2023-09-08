package com.batch.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batch.sample.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

}