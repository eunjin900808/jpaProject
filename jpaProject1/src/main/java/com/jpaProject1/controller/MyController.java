package com.jpaProject1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jpaProject1.entity.DeptDto;

@Controller
public class MyController {

	@GetMapping("/test1")
	public String test1(DeptDto dto) {
		
		return "test1";
	}

	@GetMapping("/test2")
	public String test2() {
		return "test2";
	}

	@GetMapping("/test3")
	public String test3() {
		return "test3";
	}

	@GetMapping("/test4")
	public String test4() {
		return "test4";
	}
	
}
