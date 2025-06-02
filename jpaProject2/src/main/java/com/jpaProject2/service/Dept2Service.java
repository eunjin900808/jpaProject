package com.jpaProject2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpaProject2.entity.Dept2Dto;
import com.jpaProject2.repository.Dept2Repository;

@Service
public class Dept2Service {

	private final Dept2Repository dept2Repository;
	public Dept2Service(Dept2Repository dept2Repository) {
		this.dept2Repository = dept2Repository;
	}
	
	public Dept2Dto createDept2(Dept2Dto dto) {
		return dept2Repository.save(dto);
	}
	
	public List<Dept2Dto> getFindAllRead() {
		return dept2Repository.findAll();
	}
}
