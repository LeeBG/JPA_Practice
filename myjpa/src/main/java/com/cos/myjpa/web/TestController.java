package com.cos.myjpa.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;
import com.cos.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor		//AutoWired쓰지 말고
@RestController
public class TestController {
	
	private final PostRepository postRepository;
	@PostMapping("/test/post")										//post니까 httpBody데이터를 받음
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) {		//받아야하는것 title, content 
		/**
		 * Post post = new Post();
		 * post.setTitle(postSaveReqDto.getTitle());
		 * post.setContent(postSaveReqDto.getContent());
		 */
//		User user = new User(1L,"ssar","1234","ssar@nate.com",LocalDateTime.now());				//실제로는 세션값을 넣어야 함
		
		Post postEntity = postRepository.save(postSaveReqDto.toEntity()); //toEntity() => post오브젝트로 바꿔서 응답 <실패시 Exception탄다.>
//		postEntity.setUser(user);
		
		return new CommonRespDto<>(1,"성공",postEntity);	
	}
	
	@GetMapping("/test/post")
	public CommonRespDto<?> findAll(){
		List<Post> postsEntity = postRepository.findAll();
		return new CommonRespDto<>(1,"성공",postsEntity);
	}
	
	@GetMapping("/test/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id){
		
		Post postEntity = postRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다."); //잘못된 인수가 들어온 오류이기 때문
		});			//.get() = Optional 안에 있는데이터를 꺼내라는 것
		
		return new CommonRespDto<>(1,"성공",postEntity);
	}
	
	@PutMapping("/test/post/{id}")
	public CommonRespDto<?> update(@PathVariable Long id, @RequestBody PostUpdateReqDto postUpdateReqDto){
		// 1. id에 해당하는 것 찾기
		Post postEntity = postRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());
		
		Post postUpdateEntity = postRepository.save(postEntity); // 더티체킹을 사용해야하는데 그러려면 @Service만들어야 가능함.
		
		return new CommonRespDto<>(1, "성공", postUpdateEntity);
	}
	
	@DeleteMapping("/test/post/{id}")
	public CommonRespDto<?> deleteById(@PathVariable Long id){
		postRepository.deleteById(id); 
		return new CommonRespDto<>(1, "성공", null);
	}
}
