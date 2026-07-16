package com.library.book.service;

import java.util.List;

import com.library.book.dto.BookDTO;

public interface BookService {

	public void createBook(BookDTO bookDTO);

	public void deleteBook(Long bookId);

	public void updateBook(BookDTO bookDTO);
	
	public List<BookDTO> bookList(String title);
}
