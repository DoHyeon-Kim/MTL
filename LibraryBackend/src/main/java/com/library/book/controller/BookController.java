package com.library.book.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.library.book.dto.BookDTO;
import com.library.book.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	
	@GetMapping("/booklist")
	public List<BookDTO>BookList() {
		
		return bookService.BookList();
	}
	
	@GetMapping("/booklist/search/{Search}")
	public List<BookDTO>BookSearch(@PathVariable String Search) {
		
		return bookService.BookSearch(Search);
	}
	
	@GetMapping("/bookdetail/{bookNumberInfo}")
	public List<BookDTO>BookDetail(@PathVariable Long bookNumberInfo) {
		
		return bookService.BookDetail(bookNumberInfo);
	}
	
	@GetMapping("/bookcategory")
	public List<BookDTO>BookCategory() {
		
		return bookService.BookCategory();
	}
	
	@GetMapping("/booklist/category/{category}")
	public List<BookDTO>CategoryList(@PathVariable String category)
	{
		return bookService.CategoryList(category);
	}
}
