package com.cos.myjpa.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.web.dto.CommonRespDto;
@RestController						//데이터를 리턴하기 위해 return데이터
@ControllerAdvice	//모든 Exception을 낚아챔
public class GlobalExceptionHandler {
	// 그 중에서 DataIntegrityViolationException이 발생하면 해당 함수 실행됨.
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public CommonRespDto<?> dataIntegrityViolation(Exception e){
		return new CommonRespDto<>(-1,e.getMessage(),null);		// Code , msg , data
	}
	
	// 그 중에서 IllegalArgumentException이 발생하면 해당 함수 실행됨.
	@ExceptionHandler(value = IllegalArgumentException.class)
	public CommonRespDto<?> illegalArgument(Exception e){
		return new CommonRespDto<>(-1,e.getMessage(),null);		// Code , msg , data
	}
}
