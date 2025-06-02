package com.jpaProject1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpaProject1.entity.DeptDto;

public interface DeptRepository extends JpaRepository<DeptDto,Integer> {

}
