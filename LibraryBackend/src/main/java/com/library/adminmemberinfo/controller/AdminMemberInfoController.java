package com.library.adminmemberinfo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.adminmemberinfo.DTO.AdminMemberInfoDTO;
import com.library.adminmemberinfo.service.AdminMemberInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminMemberInfoController {
	
	private final AdminMemberInfoService adminMemberInfoService;
	
	//会員ID修正検索
	@GetMapping("/admin/MemberInfoById")
	public AdminMemberInfoDTO selectAdminMemberInfo(@RequestParam("memberId") String memberId) {

	    return adminMemberInfoService.selectAdminMemberInfo(memberId);
	}
	
	//会員検索
	@GetMapping("/admin/adminMemberList")
	public List<AdminMemberInfoDTO> adminMemberList(@RequestParam("memberId") String memberId,@RequestParam("memberStatus") String memberStatus) {
		return adminMemberInfoService.adminMemberList(memberId,memberStatus);
	}
	
	//会員退会
	@DeleteMapping("/admin/adminDeleteMember")
	public int adminDeleteMember(@RequestParam("memberNo") int memberNo) {
		return adminMemberInfoService.adminDeleteMember(memberNo);
	}
	
	//会員停止解除
	@PutMapping("/admin/releaseMember")
	public int releaseMember(@RequestBody AdminMemberInfoDTO adminMemberInfoDTO) {
		System.out.println(adminMemberInfoDTO);
		return adminMemberInfoService.releaseMember(adminMemberInfoDTO);
	}
}
