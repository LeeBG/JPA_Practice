package com.cos.myjpa.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository를 원래 붙여야 하지만  생략가능함 (내부적으로 IoC에 등록됨.)
public interface PostRepository extends JpaRepository<Post, Long> {		//메모리에 뜸 IoC에 등록하는 로직이 내부에 구현되어 있음
	
}
