package com.library.book.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.library.book.dto.BookDTO;
import com.library.book.dto.BookLoanStatusDTO;

public interface BookService {

	public void createBook(BookDTO bookDTO);
	
	public BookDTO bookDetail(BookDTO bookDTO);

	List<BookLoanStatusDTO> bookStockList(Long bookNumberInfo);
	
	public void deleteBook(Long bookNumberInfo);
	
	public void bookDeleteStock(int bookNumber);

	void updateBook(String bookId, BookDTO bookDTO, MultipartFile imageFile);
	
	public List<BookDTO> bookList(String title);
}
