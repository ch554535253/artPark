package com.artPark.service;

import com.artPark.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @Author lbc on 2020/10/22  17:28.
 */
@Service
public class UserService {
    public UserDetails getUserDetails(){
        return new CustomUserDetails();
    }
}
