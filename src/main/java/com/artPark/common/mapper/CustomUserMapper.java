package com.artPark.common.mapper;

import com.artPark.security.CustomUserDetails;

public interface CustomUserMapper {
    public CustomUserDetails getCustomUserDetails(String userId);
}