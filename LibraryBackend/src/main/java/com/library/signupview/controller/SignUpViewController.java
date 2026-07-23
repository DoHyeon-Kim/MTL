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
		
		//会員登録
		@PostMapping("/sign")
		public int memberSign(
		        @RequestBody MemberDTO memberDTO,
		        HttpSession session) {

		    System.out.println(memberDTO);

		    MemberDTO loginMember =
		            (MemberDTO) session.getAttribute("loginMember");


		    if (loginMember != null
		            && "ROLE_ADMIN".equals(loginMember.getRole())) {

		        return signUpViewService.memberSign(memberDTO);
		    }


		    if (loginMember == null
		            && "ROLE_USER".equals(memberDTO.getRole())) {

		        return signUpViewService.memberSign(memberDTO);
		    }

		    return 0;
		}
}
