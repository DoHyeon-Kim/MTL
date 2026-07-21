package com.library.memberinfo.DTO;

import lombok.Data;

@Data
public class ReviewDTO {
	
	private int loanNo;
	private int bookNumber;
	private int memberNo;
	private String review;
	private int stars;
}
