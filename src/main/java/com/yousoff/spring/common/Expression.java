package com.yousoff.spring.common;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class Expression {

	@NonNull
	private String key;
	@NonNull
	private Constant.Operator operator;
	@NonNull
	private Object value;
	
}
