package com.whenling.example.repo;

import com.whenling.castle.repo.jpa.BaseJpaRepository;
import com.whenling.example.entity.FooEntity;

public interface FooEntityRepository extends BaseJpaRepository<FooEntity, Long> {

	FooEntity findByName(String name);

}
