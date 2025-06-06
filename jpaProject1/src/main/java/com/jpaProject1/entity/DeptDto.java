package com.jpaProject1.entity;

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
@Table(name="DEPARTMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeptDto {
	
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) //시퀀스가 자동으로만들어짐
	int deptno; //부서번호
	
	@Column(nullable = false, length=50)
	String deptnm; //부서이름
	
	@Column(nullable = false, length=50)
	String depttm; //팀이름


}


