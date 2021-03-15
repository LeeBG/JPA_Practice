package com.cos.myjpa.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.myjpa.filter.MyAuthenticationFilter;

// JSP에서 web.xml

@Configuration			//설정관련 클래스를 IoC 컨테이너에 (메모리에 띄움)
public class FilterConfig {
	@Bean				//return 된 어떤 값을 Ioc에 등록
	public FilterRegistrationBean<MyAuthenticationFilter> authenticationFilterRegister() {
		FilterRegistrationBean<MyAuthenticationFilter> bean=
				new FilterRegistrationBean<>(new MyAuthenticationFilter());
		
		bean.addUrlPatterns("/test");			//모든 주소에 다 요청됨
		bean.setOrder(0);
		return bean;
	}
}
