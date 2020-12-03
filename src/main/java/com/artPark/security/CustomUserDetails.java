package com.artPark.security;

import com.artPark.constant.Dict;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author lbc on 2020/10/22  17:26.
 */
@Data
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    private String userId;
    private String password;
    private String roleIds;
    private String enable;

    public CustomUserDetails(String userId){
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(roleIds != null){
            for(String roleId : roleIds.split(",")){
                authorities.add(new SimpleGrantedAuthority(roleId));
            }
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    /**
     * 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Dict.Y.equals(enable);
    }
}
