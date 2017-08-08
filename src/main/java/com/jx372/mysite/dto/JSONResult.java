package com.jx372.mysite.dto;
//json 포맷을 정해서 던져주기 위한 클래스
public class JSONResult {
	private String result;	//"success", "fail"
	private String message;	//result가 fail일때 원인 
	private Object data;	//result가 success인 경우  전달해야 할 data
	
	private JSONResult(String result, String message, Object data){
		this.result=result;
		this.message=message;
		this.data=data;
	}
	
	
	public static JSONResult success(Object data){
		return new JSONResult("success", null, data);
	}
	
	public static JSONResult error(String message){
		return new JSONResult("fail", message, null);
	}


	public String getResult() {
		return result;
	}


	public String getMessage() {
		return message;
	}


	public Object getData() {
		return data;
	}
	
	
}
