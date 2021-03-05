package com.cos.myiocdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyiocdiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyiocdiApplication.class, args);
	}

	@Bean		// Ioc (리턴값을 Ioc 컨테이너에 저장한다)
	public Robot getRobot() {
		return new Robot();
	}
}
