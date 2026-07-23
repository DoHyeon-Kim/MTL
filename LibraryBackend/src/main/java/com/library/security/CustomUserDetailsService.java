package com.library.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.security.mapper.SecurityMapper;
import com.library.signupview.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final SecurityMapper securityMapper;

    @Override
    public UserDetails loadUserByUsername(String memberId)
            throws UsernameNotFoundException {

        MemberDTO member =
                securityMapper.selectLoginMember(memberId);

        if (member == null) {
            throw new UsernameNotFoundException("該当する会員がいません。");
        }

        return new CustomUserDetails(member);
    }
}