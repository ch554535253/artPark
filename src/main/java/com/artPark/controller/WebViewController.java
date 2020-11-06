package com.artPark.controller;

import java.util.HashMap;
import java.util.Map;

import com.artPark.security.CustomUserDetails;
import com.artPark.util.Auth0JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Slf4j
@Controller
public class WebViewController {
	@Autowired
	private Auth0JwtUtil jwtUtil;

	@RequestMapping(value= {"/","loginPage"})
	public String loginPage(ServletRequest request, ServletResponse response) {
		return "order/login";
	}

	@RequestMapping(value= {"login"})
	@ResponseBody
	public Object login() {
		CustomUserDetails user = new CustomUserDetails("user");
		user.setPassword("123456");
		user.setRoleId("user");
		String token = jwtUtil.generateTokenByUser(user);
		Map<String,String> response = new HashMap<>();
		response.put("token",token);
		return response;
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
