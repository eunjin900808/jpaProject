package com.jpaProject2.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

@Entity // 데이터베이스의 테이블과 매핑되는 객체, JPA가 관리
@Table(name="BOARD3") //  DB 테이블 이름을 지정
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

	@Id // 기본키 / 기본 키(Primary Key)를 표시하는 어노테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY) //시퀀스가 자동으로만들어짐
	private int seqid;
	
	@Column(length=100,nullable=false)
	private String title;
	
	@Column(length=100,nullable=false)
	private String pass; // 암호
	
	@Column(length=50)
	private String name; // 글쓴이
	
	@Column(length=4000)
	private String content;
	
	private int hits;
	
	@CreationTimestamp	// 최초생성날짜
	private Timestamp rdate;
	
	@UpdateTimestamp	// 수정날짜
	private Timestamp udate;
}
