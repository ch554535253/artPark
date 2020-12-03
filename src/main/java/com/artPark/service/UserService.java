package com.artPark.service;

import com.artPark.common.mapper.CustomUserMapper;
import com.artPark.common.model.SysUserModel;
import com.artPark.constant.StatusCodeEnum;
import com.artPark.exception.CustomException;
import com.artPark.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author lbc on 2020/10/22  17:28.
 */
@Service
public class UserService extends BaseService implements UserDetailsService {
    @Autowired
    CustomUserMapper customUserMapper;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(userName == null || "".equals(userName)){
            throw new CustomException(StatusCodeEnum.login_failed);
        }
        CustomUserDetails user = customUserMapper.getCustomUserDetails(userName);
        if(user == null){
            throw new CustomException(StatusCodeEnum.login_failed);
        }
        return user;
    }
}
