package com.library.membermanage.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.membermanage.dto.MemberLoanStatusDTO;
import com.library.membermanage.service.MemberManageServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class MemberManageController {
	private final MemberManageServiceImpl userManageService;

	//ユーザーリスト
	@GetMapping("/userlist")
	public MemberLoanStatusDTO memberStatus(@RequestParam("memberId") String memberId) {

		return userManageService.memberList(memberId);

	}
}