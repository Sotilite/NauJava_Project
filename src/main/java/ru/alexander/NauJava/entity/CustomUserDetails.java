package ru.alexander.NauJava.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final String username;
    private final String password;
    private final Collection<GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, Collection<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
