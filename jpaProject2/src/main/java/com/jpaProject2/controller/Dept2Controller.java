package com.jpaProject2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jpaProject2.entity.Dept2Dto;
import com.jpaProject2.service.Dept2Service;

@RestController
@RequestMapping("/dept2") //대표주소
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
		
		if(dto.getDeptnm() == null || dto.getDeptnm().equals("")) {
			// 부서번호가 넘어오지 않는 경우에 삭제처리
			dept2Service.deleteById(dto.getDeptno());
		}else {			
			return dept2Service.createDept2(dto);
		}
		return dto;
	}
	
	@GetMapping
	public ModelAndView findAll() {
		ModelAndView model = new ModelAndView();
		List<Dept2Dto> list = dept2Service.getFindAllRead();
		model.setViewName("/dept/deptList");
		model.addObject("list",list);
		return model;
	}
	
	@GetMapping("/{deptno}") //기본주소는 적지않고 주소뒤에오는 키값만 적어준다
	public  ModelAndView findById(@PathVariable Integer deptno) {
		ModelAndView model = new ModelAndView();
		
		Dept2Dto dto = dept2Service.getFindById(deptno);
		model.setViewName("/dept/deptModify");
		model.addObject("dto",dto);
		return model;
	}
	
}
