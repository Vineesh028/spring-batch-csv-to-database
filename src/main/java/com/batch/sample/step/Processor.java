package com.batch.sample.step;

import org.springframework.batch.item.ItemProcessor;

import com.batch.sample.entity.UserEntity;

public class Processor implements ItemProcessor<UserEntity, UserEntity> {

	@Override
	public UserEntity process(UserEntity user) throws Exception {
		return user;
	}

}