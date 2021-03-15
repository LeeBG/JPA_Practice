package com.cos.phoneapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity		//자동으로 DB에 테이블을 만들어줌 api문서
public class Phone{
	@Id					//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //전략 Table(Long크기의 모든 연번 테이블만듦),auto_increment,Sequence,Identity(내가 사용하는 데이터베이스의 전략에 맞춰줌)
	private Long id;
	
	private String name;
	private String tel;
	
}
