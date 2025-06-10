package com.jpaProject2.controller;

import java.util.Calendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@GetMapping
	public ModelAndView calendar(String year, String month) {
		
		ModelAndView model = new ModelAndView();
		
		// 달력 클래스를 사용하기 위한 설정 (객체처리)
		Calendar cal = Calendar.getInstance(); //현재 시스템의 날짜와 시간 정보를 가진 Calendar 인스턴스를 생성
		
		
		
		// 현재 시점의 년/월 세팅
		int yy = cal.get(Calendar.YEAR); // 현재 연도 (예: 2025)
		int mm = cal.get(Calendar.MONTH); // 0 ~ 11 // 현재 월 (예: 6월이면 mm = 5) - Calendar.MONTH는 0부터 시작하기 때문에 주의
		//int yy = 2025;
		//int mm = 6; // 7월 (0-indexed 이므로 6은 실제로 7월)
		
		if( year != null && !year.equals("")) {
			yy = Integer.parseInt(year);
		}
		if( month != null && !month.equals("")) {
			mm = Integer.parseInt(month);
			mm--;
		}

		cal.set(yy,mm,1); // mm :: 0 ~ 11 // 해당 년/월의 1일로 날짜 설정
		
		// 마지막 날짜
		int lastDay = cal.getActualMaximum(Calendar.DATE); // 30 //설정한 달의 마지막 날짜 (예: 7월 ➝ 31)
		// getActualMaximum(Calendar.DATE)는 말일을 알려줌
		
		// 출력 달의 1일날의 요일 얻음
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //해당 달의 1일이 무슨 요일인지 반환
		
		System.out.println("마지막날짜:"+lastDay); // 30
		System.out.println("요일:"+dayOfWeek);    // 일(1),월(2),화(3)~~토(7)

		model.setViewName("/room/calendar");
		model.addObject("lastDay", lastDay); // 달의 마지막 날짜
		model.addObject("dayOfWeek", dayOfWeek); // 1일의 요일 (1~7)
		model.addObject("yy", yy); // 연도
		model.addObject("mm", mm); // 월 (0부터 시작 → 템플릿에서 +1 필요)
		
		return model;
	}
}

