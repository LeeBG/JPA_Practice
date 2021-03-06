package com.cos.myjpa.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.myjpa.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {		//User하나가 Post여러개와 관계하고 있다,.
	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Table, auto_increment, Sequence
	private Long id;
	private String username;
	private String password;
	private String email;

	@CreationTimestamp // 자동으로 현재시간이 들어감.
	private LocalDateTime createDate;
	
	
	// 역방향 매핑 
	@JsonIgnoreProperties({"user"})		//post안에 있는 user를 getter때리지 말라.
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)//컬럼명이아니라 변수명을 넣어 줘야한다.(나는 FK의 주인이 아니다.)FK는 user변수명 이다.
	private List<Post> post;	//관계를 맺는 것이며 데이터베이스에 전혀 영향을 주면 안됨
	
//	@Transient		//데이터베이스에 컬럼으로 만들어 지지 않지만 자바에서는 만들어짐
//	private int value;
	
	// FK를 만들고 싶은게 아님.
	// 내가 user를 select 할 때 모든 글을 보고싶을때
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // 난 연관관계의 주인이 아니야, 그러니깐 FK만들지마.
	// 왜 LAZY를 사용하는가? 내가 안들고 오고 싶을 때도 있기때문.
//	private List<Post> posts;
	
}