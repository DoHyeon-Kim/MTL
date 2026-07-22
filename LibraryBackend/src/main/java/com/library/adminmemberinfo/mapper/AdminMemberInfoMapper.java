package com.library.adminmemberinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.library.adminmemberinfo.DTO.AdminMemberInfoDTO;

@Mapper
public interface AdminMemberInfoMapper {

	//会員ID修正検索
	AdminMemberInfoDTO selectAdminMemberInfo(String memberId);
	
	//会員検索
	List<AdminMemberInfoDTO> adminMemberList(@Param("memberId") String memberId,@Param("memberStatus") String memberStatus);
	
	//会員退会
	int adminDeleteMember(int memberNo);
	int adminDeleteMemberInfo(int memberNO);
	
	//会員停止解除
	int releaseMember(AdminMemberInfoDTO adminMemberInfoDTO);
	int releaseMemberInfo(AdminMemberInfoDTO adminMemberInfoDTO);
}
