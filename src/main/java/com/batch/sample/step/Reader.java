package com.batch.sample.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.batch.sample.entity.UserEntity;

public class Reader  implements ItemReader<UserEntity> {

	@Override
	public UserEntity read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}




}