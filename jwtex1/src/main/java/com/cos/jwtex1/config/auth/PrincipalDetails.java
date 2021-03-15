package com.cos.jwtex1.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.jwtex1.domain.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{
	
	private User user;
	
	public PrincipalDetails(User user) {
		this.user=user;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		//EX) return user.getUpdateDate(); 현재날짜
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호 만료
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정활성화 돼있는지
		return true;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("롤 검증하는중");
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->"ROLE_"+user.getRole().toString());	//Role이 하나 리턴 되어서 하나 꼽힌다.
		return collectors;
	}

}
