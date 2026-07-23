package com.library.signupview.dto;
import lombok.Data;

@Data
public class MemberDTO {
	
		  private int memberNo;

		  //member
		  private String memberId;
		  private String memberPw;
		  private String role;
		  private String memberStatus;
		  
		  //memberinfo
		  private String nameH;
		  private String nameK;
		  private String phone;
		  private String email;
		  private String addNumber;
		  private String adds;
		  private String infoAdd;
		  private String bookCount;
		  
}
