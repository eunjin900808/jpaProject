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
	
	@PostMapping
	public String save(BoardDto dto) {
		String msg="ok";
		BoardDto dto1 = boardService.save(dto);
		if(dto1 == null) {
			msg = "fail";
		}
		return msg;
	}
	
	@GetMapping
	public ModelAndView list(@RequestParam(defaultValue="1") int indexpage) { // 1페이지로 설정
		ModelAndView model = new ModelAndView();
		
		Long total = boardService.count();
		//List<BoardDto> list = boardService.list();
		
		Page<BoardDto> page = boardService.list(indexpage-1,5); // indexpage는 1부터 시작 0이 셋팅되야하기때문에 -1 을 해준다
		
		// 예) 현재 페이지 번호 2번인 경우 :
		// int startPageRownum = 총데이터 개수 - (2-1)*5;
		// 화면 출력 시작 번호 = (총 데이터 개수 - (현재페이지번호-1)*출력단위)
		int startPageRownum = (int) (page.getTotalElements() - page.getNumber()*5);
		
		model.addObject("plist", page.getContent());
		//model.addObject("currentPage", page.getNumber());
		model.addObject("startPageRownum", startPageRownum);
		model.addObject("ptotal", page.getTotalElements());
		model.addObject("ptotalPage", page.getTotalPages());
		
		model.setViewName("/board/nboardList");
		return model;
	}
}
