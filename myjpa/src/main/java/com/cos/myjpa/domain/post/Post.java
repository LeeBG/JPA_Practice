package com.cos.myjpa.domain.post;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.myjpa.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Post {	//자식
	@Id					//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //전략 Table(Long크기의 모든 연번 테이블만듦),auto_increment,Sequence,Identity(내가 사용하는 데이터베이스의 전략에 맞춰줌)
	private Long id;
	@Column(length = 60,nullable = false)		//최대 길이를 지정, nullable = 초기값 true
	private String title;
	@Lob										//대용량 데이터 
	private String content;						//메시지 컨버터의 잭슨이라는애가함
	
	//누가 적었는지???
	//Post입장에서는 N대 N이다 (post:user)
	
	// 순방향 매핑 
	@ManyToOne(fetch = FetchType.EAGER)		//연관관계 맺는 법. FK의 주인인 곳에서 적어야함.
	@JoinColumn(name="userId")
	private User user;
	
	
	@CreationTimestamp 				//자동으로 현재시간이 들어감.
	private LocalDateTime createDate;
}
