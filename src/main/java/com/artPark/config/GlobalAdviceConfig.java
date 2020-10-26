package com.artPark.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@ControllerAdvice
public class GlobalAdviceConfig {
	@Autowired
	private ResourceUrlProvider resourceUrlProvider;
	
	@ExceptionHandler(Exception.class)
	public Object exceptionHandler() {
		return "error";
	}
	
//	@ModelAttribute
//	public Map<String,String> setGlobal(){
//		HashMap<String, String> mm = new HashMap<String, String>();
//		mm.put("vampire", "dsfgsfgfd");
//		System.out.println("setting vampire");
//		return mm;
//	}
	
	@ModelAttribute("urls")
	public ResourceUrlProvider urls() {
		return this.resourceUrlProvider;
	}
}
