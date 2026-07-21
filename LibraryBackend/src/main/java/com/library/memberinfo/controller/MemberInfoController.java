package com.library.memberinfo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.memberinfo.DTO.MemberInfoDTO;
import com.library.memberinfo.DTO.ReviewDTO;
import com.library.memberinfo.service.MemberInfoService;
import com.library.signupview.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;

import com.library.memberinfo.DTO.MemberInfoBookDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberInfoController {
	
	private final MemberInfoService memberInfoService;
	
	//会員情報select
	@GetMapping("/MemberInfo")
	public MemberInfoDTO selectMemberInfo(@RequestParam("memberNo") int memberNo) {
	    return memberInfoService.selectMemberInfo(memberNo);
	}
	
	//会員情報put
	@PutMapping("/MemberInfo")
	public int putMemberInfo(@RequestBody MemberInfoDTO memberInfoDTO ,HttpSession session) {
		
		MemberDTO loginMember =	(MemberDTO) session.getAttribute("loginMember");
		String loginRole = loginMember.getRole();
		
		return memberInfoService.putMemberInfo(memberInfoDTO,loginRole);
	}
	
	//会員貸出情報
	@GetMapping("/mypageBookList")
	public List<MemberInfoBookDTO> selectMyPageBookList (@RequestParam("memberNo") int memberNo,
	        @RequestParam("firstDay") LocalDate firstDay,
	        @RequestParam("secondDay") LocalDate secondDay, 
	        @RequestParam("titlesearch") String titlesearch){
		
		  List<MemberInfoBookDTO> list = memberInfoService.selectMyPageBookList(memberNo, firstDay, secondDay,titlesearch);

	return list;
	}
	
	//review登録
	@PostMapping("/review")
	public int insertReview(@RequestBody ReviewDTO reviewDTO) {
		return memberInfoService.insertReview(reviewDTO);
	}
	
	//member削除
	@DeleteMapping("/deleteMember")
	public int deleteMember(@RequestBody MemberInfoDTO memberInfoDTO,HttpSession session) {

		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
	    int memberNo = loginMember.getMemberNo();
	    
	    return memberInfoService.deleteMember(memberNo,memberInfoDTO.getMemberPw()); 
	}
}
