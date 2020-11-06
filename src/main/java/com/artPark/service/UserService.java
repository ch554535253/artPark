package com.artPark.service;

import com.artPark.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author lbc on 2020/10/22  17:28.
 */
@Service
public class UserService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        CustomUserDetails userDetails = null;
        if("user".equals(s)){
            userDetails = new CustomUserDetails(s);
            userDetails.setRoleId("user");
        }
        return userDetails;
    }
}
