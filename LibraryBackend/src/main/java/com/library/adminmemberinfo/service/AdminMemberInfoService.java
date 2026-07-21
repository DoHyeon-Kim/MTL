package com.library.adminmemberinfo.service;

import com.library.adminmemberinfo.DTO.AdminMemberInfoDTO;

public interface AdminMemberInfoService {
	
	//admin select
	AdminMemberInfoDTO selectAdminMemberInfo(String memberId);
	
}
