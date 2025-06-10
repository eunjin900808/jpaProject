package com.jpaProject2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ROOM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDto {
	
	@Id
	@Column(nullable = false)
	int roomcd; //회의실코드
	
	@Column(nullable = false, length=50)
	String roomsize; //회의실크기
	
	@Column(nullable = false, length=50)
	String roomst; //
}
