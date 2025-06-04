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
		// page : 조회할 페이지 번호 (0부터 시작)
		// size : 한 페이지당 게시글 수
		Pageable pageable = PageRequest.of(page, size);
		return boardRepository.findAll(pageable);
	}
	
	/**
	 * 총 데이터 개수
	 */
	public Long count() {
		return boardRepository.count(); // 테이블에 저장된 전체 게시글의 개수를 반환
		// 반환 타입이 Long인 이유 : → 게시글 수가 많을 수 있으므로, int보다 더 큰 범위를 다룰 수 있도록 Long을 사용
	}
}
