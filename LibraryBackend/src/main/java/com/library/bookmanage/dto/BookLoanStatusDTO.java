package com.library.bookmanage.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookLoanStatusDTO {
	
	//図書番号
	private int bookNumber;
	
	//図書リスト番号
	private Long bookNumberInfo;
	
	//貸出会員
	private String nameH;
	
	//貸出活性化
	private int loanAvailable;
	
	//貸出日
	private LocalDate loanDate;
	
	//返却予定日
	private LocalDate dueDate;
}
