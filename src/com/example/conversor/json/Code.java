package com.example.conversor.json;

public class Code {
	
	private String code;
	private String name;
	
	public Code(String code, String name) {
		setCode(code);
		setName(name);
	}
	
	private void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}
