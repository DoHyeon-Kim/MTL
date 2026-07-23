package com.library.adminmemberinfo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.library.adminmemberinfo.DTO.AdminMemberInfoDTO;

@Mapper
public interface AdminMemberInfoMapper {

	//admin select
	AdminMemberInfoDTO selectAdminMemberInfo(String memberId);
	
	
}
