package com.whenling.example.domain;

import com.whenling.main.domain.Domain;

public class Foo extends Domain<Long> {

	private static final long serialVersionUID = 7275909874359158337L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
