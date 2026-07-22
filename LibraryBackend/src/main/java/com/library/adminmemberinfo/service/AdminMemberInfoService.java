package com.library.adminmemberinfo.service;

import java.util.List;

import com.library.adminmemberinfo.DTO.AdminMemberInfoDTO;


public interface AdminMemberInfoService {
	
	//会員ID修正検索
	AdminMemberInfoDTO selectAdminMemberInfo(String memberId);
	
	//会員検索
	List<AdminMemberInfoDTO> adminMemberList(String memberI,String memberStatus);
	
	//会員退会
	int adminDeleteMember (int memberNo);
	
	//会員停止解除
	int releaseMember(AdminMemberInfoDTO adminMemberInfoDTO);
	
}
