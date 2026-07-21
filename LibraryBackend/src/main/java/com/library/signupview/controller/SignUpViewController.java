package com.library.signupview.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.signupview.dto.MemberDTO;
import com.library.signupview.service.SignUpViewService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SignUpViewController {

		private final SignUpViewService signUpViewService;
		
		//登録
		@PostMapping("/sign")
		public int memberSign(@RequestBody MemberDTO memberDTO,HttpSession session) {
			
			MemberDTO loginMember =	(MemberDTO) session.getAttribute("loginMember");
			String loginRole = loginMember.getRole();
			
			if("ROLE_ADMIN".equals(loginRole)) {
				return signUpViewService.memberSign(memberDTO);	
			}else{
				if ("ROLE_USER".equals(memberDTO.getRole())) {
					return signUpViewService.memberSign(memberDTO);
				}
			}
			return 0;
		}
}
