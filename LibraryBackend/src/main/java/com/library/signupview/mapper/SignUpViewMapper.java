package com.library.signupview.mapper;

import com.library.signupview.dto.MemberDTO;

public interface SignUpViewMapper {

	//登録
	int memberSign(MemberDTO memberDTO);
	int memberInfoSign(MemberDTO memberDTO);
	
	//select ID
	int selectSignId(String memberId);
	
	//select Email
	int selectSignEmail(String email);
}
