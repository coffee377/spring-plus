package com.voc.api.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/01/15 19:59
 */
@Slf4j
@Component("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
                SimpleGrantedAuthority roleAdmin2 = new SimpleGrantedAuthority("SCOPE_messages.read");
                return Arrays.asList(roleAdmin, roleAdmin2);
            }

            @Override
            public String getPassword() {
                return "{noop}123456";
            }

            @Override
            public String getUsername() {
                return "admin";
            }

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
                return true;
            }
        };
    }

    /**
     * 根据用户名获取用户角色信息
     *
     * @param username 用户名
     * @return Collection<GrantedAuthority>
     */
    private Collection<GrantedAuthority> getAuthorities(String username) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        return authorities;
    }

}