package com.jpaProject1.service;

import org.springframework.stereotype.Service;

import com.jpaProject1.entity.DeptDto;
import com.jpaProject1.repository.DeptRepository;

@Service
public class DeptService {
	private final DeptRepository deptRepository;
	
	public DeptService(DeptRepository deptRepository) {
		this.deptRepository = deptRepository;
	}
	
	public DeptDto createDept(DeptDto dto) {
		return deptRepository.save(dto);
	}
	
	
}
