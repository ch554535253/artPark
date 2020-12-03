package com.artPark.config;

import com.artPark.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@Slf4j
@RestControllerAdvice
public class GlobalAdviceConfig {
	@Autowired
	@Qualifier("customResourceUrlProvider")
	ResourceUrlProvider resourceUrlProvider;

	@ExceptionHandler(Exception.class)
	public Object exceptionHandler(Exception e) {
		log.error("系统错误",e);
		return "error";
	}

	@ExceptionHandler(CustomException.class)
	public Object customExceptionHandler(CustomException e) {
		log.error(e.getMsg(),e);
		return e.toString();
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
