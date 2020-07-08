package com.jiehuihui.zuul.filter;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class ExceptionHandler implements ErrorController {

	@Override
	public String getErrorPath() {
		
		return "error";
	}
	
	@RequestMapping(value="/error")
	public String error(){
		return "{\"result\":\"访问太多频繁，请稍后再访问！！！\"}";
	}

}
