package com.jpaProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpaProject2.entity.RoomDto;

public interface RoomRepository extends JpaRepository<RoomDto,Integer>{

}
