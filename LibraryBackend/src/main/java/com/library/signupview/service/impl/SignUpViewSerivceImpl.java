package com.library.signupview.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.signupview.dto.MemberDTO;
import com.library.signupview.mapper.SignUpViewMapper;
import com.library.signupview.service.SignUpViewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpViewSerivceImpl implements SignUpViewService{
	
	private final SignUpViewMapper signUpViewMapper;

	//pass
	private final PasswordEncoder passwordEncoder;
	
	//member sign
	@Override
	@Transactional
	public int memberSign(MemberDTO memberDTO) {

	    // ID 
	    if (signUpViewMapper.selectSignId(memberDTO.getMemberId()) > 0) {
	        return 2;
	    }

	    // email
	    if (signUpViewMapper.selectSignEmail(memberDTO.getEmail()) > 0) {
	        return 3;
	    }

	    // BCrypt
	    memberDTO.setMemberPw(
	        passwordEncoder.encode(memberDTO.getMemberPw())
	    );

	    int member = signUpViewMapper.memberSign(memberDTO);
	    int memberinfo = signUpViewMapper.memberInfoSign(memberDTO);

	    if (member == 0 || memberinfo == 0) {
	        throw new RuntimeException("登録失敗");
	    }

	    return 1;
	}



}
