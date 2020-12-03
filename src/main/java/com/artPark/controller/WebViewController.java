package com.artPark.controller;

import com.artPark.common.model.SysUserModel;
import com.artPark.common.vo.ResultJson;
import com.artPark.constant.Dict;
import com.artPark.constant.StatusCodeEnum;
import com.artPark.exception.CustomException;
import com.artPark.security.CustomUserDetails;
import com.artPark.service.BaseService;
import com.artPark.service.UserService;
import com.artPark.util.Auth0JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class WebViewController {
	@Autowired
	private Auth0JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private BaseService dao;

	@RequestMapping(value= {"/","loginPage"})
	public String loginPage(ServletRequest request, ServletResponse response) {
		return "order/login";
	}

	@RequestMapping(value= {"login"})
	@ResponseBody
	public Object login(@RequestBody CustomUserDetails user) {
		try {
			Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserId(),user.getPassword()));
		}catch (DisabledException e){
			log.error("用户状态已禁用",e);
			throw new CustomException(StatusCodeEnum.permission_denied);
		}catch (BadCredentialsException e){
			throw new CustomException(StatusCodeEnum.login_failed);
		}
		String token = jwtUtil.generateTokenByUser(user);
		ResultJson rsp = new ResultJson(StatusCodeEnum.success);
		rsp.setObj(token);
		return rsp;
	}

	@RequestMapping(value="registerUser")
	@ResponseBody
	public Object registerUser(@RequestBody SysUserModel user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode(user.getPassword());
		user.setPassword(password);
		user.setEnable(Dict.Y);
		dao.insert(user);
		Map<String,String> response = new HashMap<>();
		response.put("code",StatusCodeEnum.success.getCode());
		response.put("msg",StatusCodeEnum.success.getDesc());
		return response;
	}
	
	@RequestMapping(value="/api/vampire")
	public Map<String,Object> getGlobal() {
//		Map<String,Object> mm = model.asMap();
		Map<String,Object> mm = new HashMap<String, Object>();
		mm.put("dsfsdf", "deee");
//		System.out.println("getting vampire"+mm);
		return mm;
	}
	
}
