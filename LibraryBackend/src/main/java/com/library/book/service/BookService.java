package com.library.book.service;

import java.util.List;

import com.library.book.dto.BookDTO;

public interface BookService {
	List<BookDTO> BookList();

	List<BookDTO>BookSearch(String Search);
	
	List<BookDTO>BookDetail(Long bookNumberInfo);

	List<BookDTO>BookCategory();
	
	List<BookDTO>CategoryList(String category);
}
