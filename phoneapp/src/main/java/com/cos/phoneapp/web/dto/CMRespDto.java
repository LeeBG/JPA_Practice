package com.cos.phoneapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CMRespDto<T> {		//항상 받을 떄 얘를 만들어줌
	private int code;
	private T data;
}
