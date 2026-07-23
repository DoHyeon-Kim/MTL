package com.library.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.library.signupview.dto.MemberDTO;

public class CustomUserDetails implements UserDetails {

    private final MemberDTO member;

    public CustomUserDetails(MemberDTO member) {
        this.member = member;
    }
    public MemberDTO getMember() {
        return member;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(member.getRole())
        );
    }

    @Override
    public String getPassword() {
        return member.getMemberPw();
    }

    @Override
    public String getUsername() {
        return member.getMemberId();
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
    	return "ACTIVE".equals(member.getMemberStatus());
    }

}