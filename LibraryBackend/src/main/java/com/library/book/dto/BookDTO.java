package com.library.book.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookDTO {

	//図書リスト番号
	private Long bookNumber;

	//図書固有番号
	private Long bookNumberInfo;

	//図書イメージ
	private String bookImg;

	//図書名
	private String bookTitle;

	//著者
	private String Writer;

	//出版社
	private String Publisher;

	//カテゴリー
	private String Category;

	//図書説明
	private String bookContent;

	//貸出活性化
	private int loanAvailable;

	//出版日
	private LocalDate publicationDate;

	//登録日
	private LocalDateTime registrationDate;

}
