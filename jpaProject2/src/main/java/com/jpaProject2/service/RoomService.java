package com.jpaProject2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpaProject2.entity.RoomDto;
import com.jpaProject2.repository.RoomRepository;

@Service
public class RoomService {
	private final RoomRepository roomRepository;
	
	// ID 존재 여부 확인
	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}
	
	public Boolean idCheck(int roomcd) {
		return roomRepository.existsById(roomcd);
	}
	
	// 저장 또는 수정
	/**
	 * save()는 JPA의 기본 메서드:
	 * id가 있으면 → update
	 * id가 없으면 → insert
	 * 저장된 결과(dto1)를 반환함
	 **/
	public RoomDto save(RoomDto dto) {
		RoomDto dto1 = roomRepository.save(dto);
		// 저장 실패 시 dto1 = null; save()는 실패하면 예외(Exception) 를 발생시키므로 null 이 반환되지않는다
		return dto1;
	}
	
	// 전체 리스트 반환
	public List<RoomDto> list() {
		List<RoomDto> list = roomRepository.findAll();
		return list;
		
	}
	
}
