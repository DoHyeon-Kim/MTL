package com.library.signupview.dto;
import lombok.Data;

@Data
public class MemberDTO {
	
		  private int memberNo;

		  private String memberId;
		  private String memberPw;
		  private String role;
		  private String memberStatus;
}