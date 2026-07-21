package com.library.signupview.service.impl;

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

	//登録
	@Override
	@Transactional
	public int memberSign(MemberDTO memberDTO) {
		
		
		if(signUpViewMapper.selectSignId(memberDTO.getMemberId()) > 0) {
			return 2;
		}else if(signUpViewMapper.selectSignEmail(memberDTO.getEmail()) > 0){
			return 3;
		}else {
			int member = signUpViewMapper.memberSign(memberDTO);
			int memberinfo = signUpViewMapper.memberInfoSign(memberDTO);
			
			if (member == 0 || memberinfo == 0) {
		        throw new RuntimeException("登録失敗");
		    }
			
		}
		
		return 1;
		
		
		
	}



}
