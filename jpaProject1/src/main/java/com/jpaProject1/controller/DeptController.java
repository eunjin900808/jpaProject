package com.jpaProject1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jpaProject1.entity.DeptDto;
import com.jpaProject1.service.DeptService;

@RestController //jpa형태로 할때는 RestController 사용
@RequestMapping("/dept") //기본
public class DeptController {

//	@GetMapping("/write")
//	public String write() {
//		System.out.println("====== dept write ======");
//		return "dept/deptWrite"; // @RestController를 쓰게되면 경로가아닌 문자로 인식한다
//	}
	
	private final DeptService deptservice;
	
	public DeptController(DeptService deptservice) {
		this.deptservice = deptservice;
	}
	
	@GetMapping("/write")
	public ModelAndView write() { // ModelAndView 경로설정
		System.out.println("====== dept write ======");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("dept/deptWrite");
		return model;
	}
	
	@PostMapping
	public void deptSave(DeptDto dto) {
		
		System.out.println(dto.getDeptnm());
		System.out.println(dto.getDepttm());
		
		deptservice.createDept(dto);
	}
}
