package com.whenling.example.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.whenling.castle.repo.jpa.BaseEntity;

@Entity
@Table(name = "ex_foo")
public class FooEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = -6300421218134111526L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
