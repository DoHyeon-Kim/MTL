package com.library.adminmemberinfo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.adminmemberinfo.DTO.AdminMemberInfoDTO;
import com.library.adminmemberinfo.service.AdminMemberInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminMemberInfoController {
	
	private final AdminMemberInfoService adminMemberInfoService;
	
	//admin select 
	@GetMapping("/MemberInfoById")
	public AdminMemberInfoDTO selectAdminMemberInfo(@RequestParam("memberId") String memberId) {
		
	    return adminMemberInfoService.selectAdminMemberInfo(memberId);
	}
}
