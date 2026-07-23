package com.library.memberinfo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.library.memberinfo.DTO.MemberInfoDTO;
import com.library.memberinfo.DTO.ReviewDTO;

import jakarta.servlet.http.HttpSession;

import com.library.memberinfo.DTO.MemberInfoBookDTO;

public interface MemberInfoService {
	
	//会員情報select
	MemberInfoDTO selectMemberInfo (int memberNo);

	//会員情報put
	int putMemberInfo (MemberInfoDTO memberInfoDTO ,String loginRole);
	
	//会員貸出情報
	List<MemberInfoBookDTO> selectMyPageBookList(@RequestParam("memberNo") int memberNo,
	        @RequestParam("firstDay") LocalDate firstDay,
	        @RequestParam("secondDay") LocalDate secondDay, @RequestParam("titlesearch") String titlesearch);
	
	//review登録
	int insertReview (ReviewDTO reviewDTO);
	
	//member削除
	int deleteMember(int memberNo, String memberPw);
	
}
