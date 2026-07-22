package com.library.adminmemberinfo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.adminmemberinfo.DTO.AdminMemberInfoDTO;
import com.library.adminmemberinfo.mapper.AdminMemberInfoMapper;
import com.library.adminmemberinfo.service.AdminMemberInfoService;
import com.library.memberinfo.mapper.MemberInfoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminMemberInfoServiceImpl implements AdminMemberInfoService{

	private final AdminMemberInfoMapper adminMemberInfoMapper;
	
	//会員ID修正検索
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

	//会員検索
	@Override
	public List<AdminMemberInfoDTO> adminMemberList(String memberId,String memberStatus) {
		return adminMemberInfoMapper.adminMemberList(memberId,memberStatus);
	}

	//会員退会
	@Override
	@Transactional
	public int adminDeleteMember(int memberNo) {
		 
		 int MemberInfo = adminMemberInfoMapper.adminDeleteMemberInfo(memberNo);
		 int Member = adminMemberInfoMapper.adminDeleteMember(memberNo);
		 
		 if(MemberInfo == 1 && Member == 1) {
			 return 1;
		 }
		 return 0;
	}

	//会員停止解除
	@Override
	@Transactional
	public int releaseMember(AdminMemberInfoDTO adminMemberInfoDTO) {
		
		int member = adminMemberInfoMapper.releaseMember(adminMemberInfoDTO);
		int memberInfo = adminMemberInfoMapper.releaseMemberInfo(adminMemberInfoDTO);
		
		 if(memberInfo == 1 && member == 1) {
			 return 1;
		 }
		 return 0;
	}


}
