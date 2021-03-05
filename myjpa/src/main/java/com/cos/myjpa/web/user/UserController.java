package com.cos.myjpa.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.domain.user.UserRepository;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.user.dto.UserJoinReqDto;
import com.cos.myjpa.web.user.dto.UserLoginReqDto;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class UserController{
	
	private final UserRepository userRepository;
	private final HttpSession session; 	//DI	

	@PostMapping("/join")		//auth(인증)
	public CommonRespDto<?> join(@RequestBody UserJoinReqDto userJoinReqDto){
		User userEntity = userRepository.save(userJoinReqDto.toEntity());
		return new CommonRespDto<>(1,"성공",userEntity);
	}
	
	@PostMapping("/login")		//login(로그인)
	public CommonRespDto<?> login(@RequestBody UserLoginReqDto userLoginReqDto){
		User userEntity = userRepository.findByUsernameAndPassword(userLoginReqDto.getUsername(), userLoginReqDto.getPassword());
		if(userEntity==null) {
			return new CommonRespDto<>(-1,"실패",null);
		}else {
			userEntity.setPassword(null);			//패스워드를 그대로 넘기는 것은 위험한 일
			session.setAttribute("principal", userEntity);
			return new CommonRespDto<>(1,"성공",userEntity);
		}
	}
	
	//샘플
	@GetMapping("/test/user/{id}")	//유저 정보
	public CommonRespDto<?> userInfo(@PathVariable Long id){
		User principal = (User) session.getAttribute("principal");
		if(principal == null) {
			return new CommonRespDto<>(-1,"실패",null);
		}else {
			User userEntity = userRepository.findById(id).get();
			return new CommonRespDto<>(1,"성공",userEntity);
		}
	}
}
