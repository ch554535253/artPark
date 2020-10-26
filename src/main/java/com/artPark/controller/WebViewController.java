package com.artPark.controller;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class WebViewController {

	@RequestMapping(value= {"/","login"})
	public String login() {
		log.error("log in error");
		log.warn("log in warn");
		log.info("log in info");
		log.debug("log in debug");
		log.trace("log in trace");
		return "order/login";
	}
	
	@RequestMapping(value="/api/vampire")
	@ResponseBody
	public Map<String,Object> getGlobal() {
//		Map<String,Object> mm = model.asMap();
		Map<String,Object> mm = new HashMap<String, Object>();
		mm.put("dsfsdf", "deee");
//		System.out.println("getting vampire"+mm);
		return mm;
	}
	
}
