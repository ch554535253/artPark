package com.artPark.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lbc on 2020/10/19  16:50.
 */
@RestController
public class OrderController {
//    @Autowired
//    private SysUserModelMapper sysUserModelMapper;

    @RequestMapping(value="testC")
    public Object testC(){
        Map<String,String> aa = new HashMap<>();
        aa.put("aaa","123");
        aa.put("bbb","123");
        return aa;
    }

    public static void main(String[] args) {
        String str = "1234567";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd1 = encoder.encode(str);
        String pwd2 = encoder.encode(str);
        System.out.println("第一次加密:"+pwd1);
        System.out.println("第二次加密："+pwd2);
        System.out.println("匹配结果1:"+encoder.matches(str,pwd2));
        System.out.println("匹配结果2:"+encoder.matches("12345678",pwd2));
    }
}
