package com.cos.myiocdi;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import lombok.Getter;

//Component는 런타임(실행될때) IOC 컨테이너에 등록됨
//@Component		//Ioc(특별한 용도가 없으면 이거 사용)
//@Configuration		// 설정파일
//@Service				// 서비스
//@Repository				// 데이터베이스에서 들고온 레포지토리
@Component
@Getter
public class Robot {
	private String name = "건담";
}
