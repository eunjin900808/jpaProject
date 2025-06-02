package com.jpaProject2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jpaProject2.entity.Dept2Dto;
import com.jpaProject2.service.Dept2Service;

@RestController
@RequestMapping("/dept2")
public class Dept2Controller {

	//서비스연결
	private final Dept2Service dept2Service;
	public Dept2Controller(Dept2Service dept2Service) {
		this.dept2Service = dept2Service;
	}
	
	//화면 띄우기
	@GetMapping("/write")
	public ModelAndView write() {
		ModelAndView model=new ModelAndView();
		model.setViewName("/dept/deptWrite");
		return model;
	}
	
	@PostMapping
	public Dept2Dto save(Dept2Dto dto) {
		return dept2Service.createDept2(dto);
	}
	
	@GetMapping
	public ModelAndView findAll() {
		ModelAndView model = new ModelAndView();
		List<Dept2Dto> list = dept2Service.getFindAllRead();
		model.setViewName("/dept/deptList");
		model.addObject("list",list);
		return model;
	}
	
}
