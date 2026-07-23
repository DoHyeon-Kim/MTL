package com.library.book.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BookDTO {
	private int bookNumber;
	private Long bookNumberInfo;
	private String bookImg;
	private String bookTitle;
	private String 	Writer;
	private String Publisher;
	private String 	Category;
	private String 	bookContent;
	private boolean loanAvailable;
	private Date publicationDate;
	private Date registrationDate;

}
