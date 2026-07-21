package com.library.adminmemberinfo.service.impl;

import org.springframework.stereotype.Service;

import com.library.adminmemberinfo.DTO.AdminMemberInfoDTO;
import com.library.adminmemberinfo.mapper.AdminMemberInfoMapper;
import com.library.adminmemberinfo.service.AdminMemberInfoService;
import com.library.memberinfo.mapper.MemberInfoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminMemberInfoServiceImpl implements AdminMemberInfoService{

	private final AdminMemberInfoMapper adminMemberInfoMapper;
	
	//admin select
	@Override
	public AdminMemberInfoDTO selectAdminMemberInfo(String memberId) {

		AdminMemberInfoDTO member = adminMemberInfoMapper.selectAdminMemberInfo(memberId);


	    if (member == null) {
	        return null;
	    }

	    if ("ROLE_ADMIN".equals(member.getRole())) {
	        AdminMemberInfoDTO dto = new AdminMemberInfoDTO();
	        dto.setMemberId("ADMIN_BLOCK");   
	        return dto;
	    }

	    return member;
	}

	
	


}
