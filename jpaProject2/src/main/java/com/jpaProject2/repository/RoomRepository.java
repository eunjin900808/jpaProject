package com.jpaProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpaProject2.entity.RoomDto;

public interface RoomRepository extends JpaRepository<RoomDto,Integer>{

	//ROOM 테이블에서 ROOMCD의 최대값을 구해 +1 하여 새로운 ROOMCD를 자동 생성하기 위한 기능
	//데이터가 없다면 기본값 101부터 시작
	//자동으로 ROOMCD를 지정하기 위한 용도
	
	@Query(value="SELECT NVL(MAX(ROOMCD),100)+1 FROM ROOM",nativeQuery=true)
	int findByNative();
	
	//@Query : Spring Data JPA의 어노테이션 /지정한 SQL 쿼리를 실행하도록 지정함
	//ROOM 테이블의 ROOMCD 컬럼에서 가장 큰 값을 가져옴 (현재 사용 중인 가장 큰 방 코드)
	//MAX(ROOMCD) 값이 NULL일 경우(즉, 데이터가 아예 없을 경우) 100으로 대체
	//다음 방 코드를 생성하기 위해 최대값 + 1을 반환
	//결과적으로, ROOMCD 컬럼에서 가장 큰 값 + 1을 반환하되, 데이터가 없으면 101을 반환
	//nativeQuery=true → JPQL이 아닌 진짜 SQL을 그대로 사용
	//쿼리 결과는 숫자 하나 (int) - 메서드 이름 findByNative()는 단순히 쿼리를 실행해서 그 값을 반환
}
