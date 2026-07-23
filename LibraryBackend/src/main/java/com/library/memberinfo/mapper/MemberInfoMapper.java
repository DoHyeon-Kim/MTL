package com.library.memberinfo.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.library.memberinfo.DTO.MemberInfoBookDTO;
import com.library.memberinfo.DTO.MemberInfoDTO;
import com.library.memberinfo.DTO.ReviewDTO;

public interface MemberInfoMapper {

	//会員情報検索
	MemberInfoDTO selectMemberInfo(int memberNo);

	//会員情報修正
	int putMemberInfo(MemberInfoDTO memberInfoDTO);
	int putMember(MemberInfoDTO memberInfoDTO);
	
	int adminPutMemberInfo(MemberInfoDTO memberInfoDTO);
	int adminPutMember(MemberInfoDTO memberInfoDTO);
	
	//admin
	
	//会員貸出情報
	List<MemberInfoBookDTO> selectMyPageBookList(@Param("memberNo") int memberNo,
			@Param("firstDay") LocalDate firstDay,
			@Param("secondDay") LocalDate secondDay,
			@Param("titlesearch") String titlesearch);
	
	//review登録
	int insertReview(ReviewDTO reviewDTO);
	
	//会員削除
	String pwCheck(int memberNo);
	
	int deleteMember(int memberNo);
	
	int deleteMemberinfo(int memberNo);
}
