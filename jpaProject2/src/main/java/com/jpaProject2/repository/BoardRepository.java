package com.jpaProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpaProject2.entity.BoardDto;

public interface BoardRepository extends JpaRepository<BoardDto,Integer>{

}
