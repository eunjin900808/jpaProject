package com.jpaProject2.entity;

import com.jpaProject2.entity.Dept2Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="DEPARTMENT2")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dept2Dto {
	
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) //시퀀스가 자동으로만들어짐
	Integer deptno; //부서번호
	
	@Column(nullable = false, length=50)
	String deptnm; //부서이름
	
	@Column(nullable = false, length=50)
	String depttm; //팀이름

	@Column(nullable = false, length=50)
	String deptst; //팀 상태 :: Y/N :: 

 // INSERT -> Key 값 생략(키값을 전달해주지않으면 insert 로 인식한다)
 // UPDATE -> Key 값 포함
	
}
