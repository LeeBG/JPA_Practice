package com.cos.myjpa.web.post;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

/**
 * 
 * ORM = Object Relation Mapping (자바 오브젝트를 먼저 만들고 DB에 테이블을 자동 생성, 자바오브젝트로 연관관계를 맺을 수 있음)
 * JPA = JAVA 오브젝트를 영구적으로 저장하기 위한 인터페이스 = 함수()
 *
 * h2 내장되어 있음 = 원래는 h2도 설치를 해야하는데 스프링에 내장되어있음
 * 스프링부트에서는 h2가 가장 편하다
 */

@RequiredArgsConstructor
@RestController
public class PostController {

	private final PostRepository postRepository;
	private final HttpSession session;
	
	@PostMapping("/post")										//post니까 httpBody데이터를 받음
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) {		//받아야하는것 title, content 
		/**
		 * Post post = new Post();
		 * post.setTitle(postSaveReqDto.getTitle());
		 * post.setContent(postSaveReqDto.getContent());
		 */
		
//		User user = new User(1L,"ssar","1234","ssar@nate.com",LocalDateTime.now());				//실제로는 세션값을 넣어야 함
		
		User principal = (User) session.getAttribute("principal");
		
		if(principal == null) {
			return new CommonRespDto<>(-1,"실패",null);
		}
		
//		User principal = new User();
//		principal.setUsername("cos");
//		principal.setPassword("1234");
		
		
		Post post = postSaveReqDto.toEntity();			//post오브젝트를 만들어주는데 title content
		post.setUser(principal);						//userId
		Post postEntity = postRepository.save(post); //toEntity() => post오브젝트로 바꿔서 응답 <실패시 Exception탄다.>
										//연관관계 무시하고 save()
		//실패는 Exception탄다
		return new CommonRespDto<>(1,"성공",postEntity);	
	}
	
	@GetMapping("/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id){
		
		Post postEntity = postRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다."); //잘못된 인수가 들어온 오류이기 때문
		});			//.get() = Optional 안에 있는데이터를 꺼내라는 것
		
//		postEntity.getUser();	//비어있으면 자동을 select해줌 => 영속성 컨텍스트
		
		return new CommonRespDto<>(1,"성공",postEntity);	//MessageCoverter가 모든 getter를 다 호출해서 JSON으로 만들어준다.
	}
}