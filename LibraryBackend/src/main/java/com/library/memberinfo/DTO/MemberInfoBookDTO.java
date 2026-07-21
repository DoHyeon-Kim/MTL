package com.library.memberinfo.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MemberInfoBookDTO {
	
	private int memberNo;
	private String firstDay;
	private String secondDay;
	
	private int stars;
	private int loanNo;
	private LocalDate loanDate;
	private LocalDate returnDate;
	private String bookTitle;
	private String bookImg;
	private String Writer;
	private String bookNumber;
	
	private String titlesearch;
}
