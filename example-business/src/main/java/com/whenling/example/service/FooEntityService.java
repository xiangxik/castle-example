package com.whenling.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whenling.castle.main.service.EntityService;
import com.whenling.example.entity.FooEntity;
import com.whenling.example.repo.FooEntityRepository;

@Service
public class FooEntityService extends EntityService<FooEntity, Long> {

	@Autowired
	private FooEntityRepository fooEntityRepository;

	public FooEntity findByName(String name) {
		return fooEntityRepository.findByName(name);
	}
}
