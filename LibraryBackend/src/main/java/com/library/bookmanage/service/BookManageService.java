package com.library.bookmanage.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.library.bookmanage.dto.BookLoanStatusDTO;
import com.library.bookmanage.dto.BookManageDTO;

public interface BookManageService {

	public void createBook(BookManageDTO bookDTO);
	
	public BookManageDTO bookDetail(BookManageDTO bookDTO);

	List<BookLoanStatusDTO> bookStockList(Long bookNumberInfo);
	
	public void deleteBook(Long bookNumberInfo);
	
	public void bookDeleteStock(int bookNumber);

	void updateBook(String bookId, BookManageDTO bookDTO, MultipartFile imageFile);
	
	public List<BookManageDTO> bookList(String title);
}
