package com.jpaProject2.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jpaProject2.entity.BoardDto;
import com.jpaProject2.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/write")
	public ModelAndView write() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/board/nboardWrite");
		return model;
	}
	
	@PostMapping ///save 같은 경로에 대한 POST 요청을 처리
	public String save(BoardDto dto) {
		String msg="ok"; // 기본적으로 결과 메시지를 "ok"로 설정
		BoardDto dto1 = boardService.save(dto); //저장된 결과(예: DB에 저장된 객체)가 dto1에 반환됨
		if(dto1 == null) {
			msg = "fail"; //만약 저장 결과가 null 이라면(즉, 저장 실패), 메시지를 "fail"로 바꿈
		}
		return msg; // 최종적으로 저장 성공이면 "ok", 실패면 "fail" 문자열을 클라이언트에 반환
	}
	
	@GetMapping
	public ModelAndView list(@RequestParam(defaultValue="1") int indexpage) { // indexpage 변수를 URL에서 받는다. (예: /board/list?indexpage=2 → indexpage = 2)
		// @RequestParam(defaultValue="1") :::  값이 없으면 기본값 1로 처리
		ModelAndView model = new ModelAndView();
		
		Long total = boardService.count(); // 전체 게시글 수를 가져옴

		//List<BoardDto> list = boardService.list();
		// 게시글을 페이지 단위로 조회
		Page<BoardDto> page = boardService.list(indexpage-1,5); // indexpage는 1부터 시작 0이 셋팅되야하기때문에 -1 을 해준다
			//indexpage - 1: 페이지 인덱스는 0부터 시작하므로, 사용자가 1페이지를 요청해도 내부에서는 0페이지로 처리
			//5: 한 페이지당 5개의 게시글을 가져옴
		
		// 예) 현재 페이지 번호 2번인 경우 :
		// int startPageRownum = 총데이터 개수 - (2-1)*5;
		// 화면 출력 시작 번호 = (총 데이터 개수 - (현재페이지번호-1)*출력단위)
		int startPageRownum = (int) (page.getTotalElements() - page.getNumber()*5);
		// 게시판에서 번호를 거꾸로 매기는 경우(최신 글이 위에 오고 번호가 내려감) 사용하는 방식
		
		model.addObject("plist", page.getContent());
		// 현재 페이지의 게시글 리스트(List<BoardDto>)를 모델에 담
		//model.addObject("currentPage", page.getNumber());
		model.addObject("startPageRownum", startPageRownum);
		model.addObject("ptotal", page.getTotalElements()); // 전체 게시글 수
		model.addObject("ptotalPage", page.getTotalPages()); // 전체 페이지 수
		
		model.setViewName("/board/nboardList");
		return model;
	}
}
