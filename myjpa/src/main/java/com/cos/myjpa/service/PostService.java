package com.cos.myjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.web.post.dto.PostRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;
import com.cos.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;

// JPA 영속성컨텍스트는 변경감지를 하는데 언제하느냐? 서비스 종료시에 함!!

@RequiredArgsConstructor
@Service
public class PostService {
	private final PostRepository postRepository;
	
	@Transactional
	public Post 글쓰기(PostSaveReqDto postSaveReqDto, User principal) { // title, content

		Post post = postSaveReqDto.toEntity();
		post.setUser(principal); // FK를 insert할 수 있다.
		Post postEntity = postRepository.save(post); // 실패 => Exception을 탄다.
		
		return postEntity;
	}
	
	@Transactional(readOnly = true) // 변경감지 안함, 고립성!!
	public PostRespDto 한건찾기(@PathVariable Long id){

		// 옵셔널 get(), orElseGet(), orElseThrow()
		Post postEntity = postRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		PostRespDto postRespDto = new PostRespDto(postEntity);
		
		return postRespDto;
	}
	
	@Transactional(readOnly = true)
	public List<Post> 전체찾기(){
		List<Post> postsEntity = postRepository.findAll();
		return postsEntity;
	}
	
	@Transactional
	public Post 수정하기(Long id, PostUpdateReqDto postUpdateReqDto){
		// 영속화
		Post postEntity = postRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());	
		
		return postEntity;
	} // 서비스 종료시 영속성 컨텍스트에 영속화 되어있는 모든 객체의 변경을 감지하여 변경된 아이들을 flush 한다. (commit) = 더티체킹
	
	@Transactional
	public void 삭제하기(Long id){ // 삭제는 리턴이 필요없다. 삭제하다가 오류나서 GlobalException으로 처리하면 됨.
		postRepository.deleteById(id);		 
	}
}
