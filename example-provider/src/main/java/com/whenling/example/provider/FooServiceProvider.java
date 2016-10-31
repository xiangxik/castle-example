package com.whenling.example.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whenling.example.api.FooService;
import com.whenling.example.domain.Foo;
import com.whenling.example.entity.FooEntity;
import com.whenling.example.service.FooEntityService;

@com.alibaba.dubbo.config.annotation.Service
@Service
public class FooServiceProvider implements FooService {

	@Autowired
	private FooEntityService fooEntityService;

	@Override
	public Foo findByName(String name) {
		return toDomain(fooEntityService.findByName(name));
	}

	public Foo toDomain(FooEntity entity) {
		if(entity == null) {
			return null;
		}
		Foo foo = new Foo();
		foo.setId(entity.getId());
		foo.setName(entity.getName());
		return foo;
	}

}
