package com.library.book.service;

import java.util.List;

import com.library.book.dto.BookDTO;
import com.library.book.dto.BookLoanStatusDTO;

public interface BookService {

	public void createBook(BookDTO bookDTO);
	
	public BookDTO bookDetail(BookDTO bookDTO);

	List<BookLoanStatusDTO> bookStockList(Long bookNumberInfo);
	
	public void deleteBook(Long bookId);

	public void updateBook(BookDTO bookDTO);
	
	public List<BookDTO> bookList(String title);
}
