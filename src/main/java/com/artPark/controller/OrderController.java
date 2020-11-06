package com.artPark.controller;

import com.artPark.common.mapper.SysUserModelMapper;
import com.artPark.common.model.SysUserModel;
import com.artPark.common.model.SysUserModelExample;
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
public class OrderController {
    @Autowired
    private SysUserModelMapper sysUserModelMapper;

    @RequestMapping(value = "/api/userInfo")
    @PreAuthorize("hasAnyAuthority('admin')")
    public List getUserInfo(){
        List<SysUserModel> suList = sysUserModelMapper.selectByExample(new SysUserModelExample());
        return suList;
    }

}
