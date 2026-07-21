package com.library.memberinfo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.memberinfo.DTO.MemberInfoBookDTO;
import com.library.memberinfo.DTO.MemberInfoDTO;
import com.library.memberinfo.DTO.ReviewDTO;
import com.library.memberinfo.mapper.MemberInfoMapper;
import com.library.memberinfo.service.MemberInfoService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class memberinfoserviceimpl implements MemberInfoService{

	private final MemberInfoMapper memberInfoMapper;

	//会員情報select
	@Override
	public MemberInfoDTO selectMemberInfo(int memberNo) {

		return memberInfoMapper.selectMemberInfo(memberNo);
	}

	//会員情報put
	@Override
	@Transactional
	public int putMemberInfo(MemberInfoDTO memberInfoDTO, String loginRole) {
		int member = 0;
		int memberinfo = 0;
		
		if("ROLE_USER".equals(loginRole)) {
			member = memberInfoMapper.putMember(memberInfoDTO);
			memberinfo = memberInfoMapper.putMemberInfo(memberInfoDTO);
		}else if("ROLE_ADMIN".equals(loginRole)) {
			member = memberInfoMapper.adminPutMember(memberInfoDTO);
			memberinfo = memberInfoMapper.adminPutMemberInfo(memberInfoDTO);
		}else if("ROLE_SUBADMIN".equals(loginRole)) {
			member = memberInfoMapper.adminPutMember(memberInfoDTO);
			memberinfo = memberInfoMapper.adminPutMemberInfo(memberInfoDTO);
		}
		


		
		if (member == 0 || memberinfo == 0) {
	        throw new RuntimeException("修正失敗");
	    }
		return 1;
	}

	//会員貸出情報
	@Override
	public List<MemberInfoBookDTO> selectMyPageBookList(int memberNo, LocalDate firstDay, LocalDate secondDay ,String titlesearch) {
		return memberInfoMapper.selectMyPageBookList(memberNo, firstDay, secondDay,titlesearch);
	}

	//review登録
	@Override
	public int insertReview(ReviewDTO reviewDTO) {
		return memberInfoMapper.insertReview(reviewDTO);
	}

	//member削除
	@Override
	public int deleteMember(int memberNo, String memberPw) {
		
		if(memberInfoMapper.pwCheck(memberNo).equals(memberPw)) {
			memberInfoMapper.deleteMemberinfo(memberNo);
			memberInfoMapper.deleteMember(memberNo);
			return 1;
		}
		
		return 0;
	}

	
	
}
