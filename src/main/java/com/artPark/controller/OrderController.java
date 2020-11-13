package com.artPark.controller;

import com.artPark.common.mapper.BasicController;
import com.artPark.common.model.SysUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lbc on 2020/10/19  16:50.
 */
@RestController
public class OrderController extends BasicController {
//    @Autowired
//    private SysUserModelMapper sysUserModelMapper;

    @RequestMapping(value = "/api/userInfo")
//    @PreAuthorize("hasAnyAuthority('admin')")
    public Object getUserInfo(){
        SysUserModel a = new SysUserModel();
        a.setUserId(1);
        List<SysUserModel> suList = this.find(a);
        return suList;
    }

}
