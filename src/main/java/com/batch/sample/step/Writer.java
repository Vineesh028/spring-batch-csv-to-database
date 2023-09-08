package com.batch.sample.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.sample.entity.UserEntity;
import com.batch.sample.repository.UserRepository;

public class Writer implements ItemWriter<UserEntity> {

	@Autowired
	UserRepository repository;

	@Override
	public void write(List<? extends UserEntity> users) throws Exception {
		repository.saveAll(users);

	}

}