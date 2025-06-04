package com.jpaProject2.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jpaProject2.entity.BoardDto;
import com.jpaProject2.repository.BoardRepository;

@Service
public class BoardService {

	public final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public BoardDto save(BoardDto dto) {
		return boardRepository.save(dto);
	}
	
	/**
	 * 목록 출력
	 */
	//public List<BoardDto> list() {
	public Page<BoardDto> list(int page,int size) { // 출력페이지 개수, 화면에 출력할페이지 개수
		
		Pageable pageable = PageRequest.of(page, size);
		return boardRepository.findAll(pageable);
	}
	
	/**
	 * 총 데이터 개수
	 */
	public Long count() {
		return boardRepository.count();
	}
}
