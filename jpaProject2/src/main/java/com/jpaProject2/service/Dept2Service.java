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
	
	/**
	 * 상세보기
	 */	
	public Dept2Dto getFindById(Integer deptno) { //findById : 상세보기 처리관련 메소드
		//orElse(null) : 널값 허용 안함
		return dept2Repository.findById(deptno).orElse(null);
	}
	
	/**
	 * 삭제처리
	 */
	public void deleteById(Integer deptno) { // 삭제처리에는 return 이 필요없다
		dept2Repository.deleteById(deptno);
	}
}
