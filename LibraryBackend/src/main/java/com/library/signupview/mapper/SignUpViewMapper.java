package com.library.signupview.mapper;

import com.library.signupview.dto.MemberDTO;

public interface SignUpViewMapper {

	//会員登録
	int memberSign(MemberDTO memberDTO);
	int memberInfoSign(MemberDTO memberDTO);
	
	//ID検索
	int selectSignId(String memberId);
	
	//Email検索
	int selectSignEmail(String email);
}
