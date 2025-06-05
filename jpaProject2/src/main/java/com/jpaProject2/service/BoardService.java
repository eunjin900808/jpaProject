package com.jpaProject2.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	/**
	 * 등록/ 수정 / 삭제 처리를 진행함
	 */
	public BoardDto save(BoardDto dto) {
		// 새 글 등록 시: seqid가 0이므로 조건문을 통과하지 않고 그대로 저장
		/**
		 * 수정 처리인 경우 :: 조회수와 등록날짜를 DB에서 받아와 세팅한다.
		 * 		why? update SQL은 일괄처리 이므로 화면에서 넘어오지 않은 데이터는
		 * 			 DB로 부터 받아와 세팅하고 다시 update 처리한다.
		 */
		if(dto.getSeqid() > 0 && 
			dto.getTitle() != null &&
			!dto.getTitle().equals("")) { // 일반적으로 seqid가 0보다 크면 이미 존재하는 게시글,
			//0이면 새 게시글을 의미 / 제목이 null값이지 않는경우에만 저장 /제목이 비어 있지 않은 경우에만 저장
			
			BoardDto dto1 = detail(dto.getSeqid());
			// 기존 게시글의 전체 데이터를 DB에서 다시 조회해서 가져옴
			dto.setHits(dto1.getHits());
			// 기존 게시글의 조회수(hits)를 새로운 dto 객체에 그대로 복사해서 유지
			dto.setRdate(dto1.getRdate());
			// 등록일(rdate)도 기존 게시글의 값을 유지하도록 복사
		}
		
		/**
		 * 등록과 수정은 save(dto) 메소드를 실행하고
		 * 삭제는 deleteById(키값) 메소드를 실행한다.
		 */
		if(dto.getSeqid() > 0 && dto.getTitle().equals("")) {
			boardRepository.deleteById(dto.getSeqid());
			// 기존 글인데 제목이 빈 문자열이면 삭제
		}else {
			boardRepository.save(dto);
			// 새 글이거나 제목이 존재하는 수정이면 저장
		}
		return dto;
		// 최종적으로 dto 객체를 DB에 저장. save() 메서드는 기본키(seqid)가 존재하면 업데이트(update), 없으면 새로 삽입(insert)
	}
	
	/**
	 * 목록 출력
	 */
	//public List<BoardDto> list() {
	public Page<BoardDto> list(int page,int size) { // 출력페이지 개수, 화면에 출력할페이지 개수
		// page : 조회할 페이지 번호 (0부터 시작)
		// size : 한 페이지당 게시글 수
		Pageable pageable = PageRequest.of(page, size, Sort.by("seqid").descending());
		// PageRequest.of(현페이지번호, 화면출력단위, 정렬방식);
		// Pageable : 페이징처리를 위한 스프링에서 제공하는 인터페이스 /데이터를 "페이지 단위"로 가져오려고 설정하는 코드
		// PageRequest.of(page, size) : "몇 페이지를, 몇 개씩 가져올지" 설정하는 객체
		return boardRepository.findAll(pageable);
		// findAll(pageable)은 Page<BoardDto> 타입의 결과 / 한 페이지씩 잘라서 가져와서 반환하는 코드
	}
	
	/**
	 * 총 데이터 개수
	 */
	public Long count() {
		return boardRepository.count(); // 테이블에 저장된 전체 게시글의 개수를 반환
		// 반환 타입이 Long인 이유 : → 게시글 수가 많을 수 있으므로, int보다 더 큰 범위를 다룰 수 있도록 Long을 사용
	}
	
	/**
	 * 상세보기
	 */
	public BoardDto detail(int seqid) {
		return boardRepository.findById(seqid).orElse(null);
		//seqid라는 ID 값을 기준으로
		//👉 boardRepository에서 해당 게시글을 찾아서 반환
		//👉 만약 못 찾으면 null을 반환
	}
	
	/**
	 * 조회수 증가
	 */
	public BoardDto saveHits(int seqid) {
		BoardDto dto = detail(seqid); // boardRepository.findById(seqid).orElse(null);
		int hits = dto.getHits();

		hits++;
		dto.setHits(hits); // 증가된 조회수(hits)를 다시 DTO에 설정
		//dto.setHits(hits+1); // 조회수 1 증가
		//dto.setHits(hits++); // 안됨(후위 증가 연산자) -먼저 hits의 현재 값이 dto.setHits()에 전달 -> hits 값이 1 증가
		//dto.setHits(++hits); // 됨(전위 증가 연산자) - 먼저 hits의 값을 1 증가 -> 증가된 값이 dto.setHits()에 전달
		return boardRepository.save(dto); // DB에 반영
		//dto :: save시 키값이 포함 되어있는 경우 update 처리함
		
	}
	
}
