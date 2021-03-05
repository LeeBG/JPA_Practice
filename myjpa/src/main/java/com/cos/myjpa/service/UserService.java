package com.cos.myjpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.domain.user.UserRepository;
import com.cos.myjpa.web.user.dto.UserJoinReqDto;
import com.cos.myjpa.web.user.dto.UserLoginReqDto;
import com.cos.myjpa.web.user.dto.UserRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI
@Service // IoC메모리에 띄운다.
public class UserService {

	private final UserRepository userRepository;

	public List<UserRespDto> 전체찾기() {
		List<User> usersEntity = userRepository.findAll();
		List<UserRespDto> userRespDtos = new ArrayList<>();
		for (User user : usersEntity) {
			userRespDtos.add(new UserRespDto(user));
		}
		return userRespDtos;
	}

	public UserRespDto 한건찾기(Long id) {
		// 옵셔널 get(), orElseGet(), orElseThrow()
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다."); // 잘못된 인수가 들어온 오류이기 때문
		}); // .get() = Optional 안에 있는데이터를 꺼내라는 것

		UserRespDto userRespDto = new UserRespDto(userEntity); // DTO
		return userRespDto;
	}

	public User 프로파일(Long id) {
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다."); //잘못된 인수가 들어온 오류이기 때문
		});			//.get() = Optional 안에 있는데이터를 꺼내라는 것
		
		return userEntity;
	}

	public User 로그인(UserLoginReqDto userLoginReqDto) {
		User userEntity = userRepository.findByUsernameAndPassword(userLoginReqDto.getUsername(), userLoginReqDto.getPassword());
		return userEntity;
	}

	@Transactional		//ex)송금 / write하는 것 은 트랜잭션을 발동시킴 다른애가 동시접근 못하게 lock을 검, 실패하면 롤백해줌
	public User 회원가입(UserJoinReqDto userJoinReqDto) {
		User userEntity = userRepository.save(userJoinReqDto.toEntity());
		return userEntity;
	}
}
