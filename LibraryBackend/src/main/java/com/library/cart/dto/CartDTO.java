package com.library.cart.dto;

import lombok.Data;

@Data
public class CartDTO {
	private int cartItemNo;
	private int memberNo;
	private int bookNumber;
	
	private String bookTitle;
	private String bookImg;
	private String writer;
	private boolean loanAvailable; 
}
