package com.jpaProject2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jpaProject2.entity.RoomDto;
import com.jpaProject2.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	public final RoomService roomService;
	
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}
	
	@GetMapping("/write")
	public ModelAndView write() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("/room/roomWrite");
		return model;
	}
	
	@PostMapping
	public String save(RoomDto dto) {
		
		String msg ="1";
		//중복 확인
		Boolean bool = roomService.idCheck(dto.getRoomcd());

		// 중복되지 않은 경우
		if( bool == false ) {			
		RoomDto dto1 = roomService.save(dto);
		if(dto1 == null) msg = "4";
		}else {
			//이미 사용중인 경우
			msg = "2";
		}
		return msg;
	}
	
	@GetMapping
	public ModelAndView list() {
		ModelAndView model = new ModelAndView();
		List<RoomDto> list = roomService.list();
		model.addObject("list",list);
		model.setViewName("/room/roomList");
		
		return model;
		
	}

}
