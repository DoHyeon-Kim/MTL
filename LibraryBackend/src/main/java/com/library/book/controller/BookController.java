package com.library.book.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.book.dto.BookDTO;
import com.library.book.service.BookServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {
	private final BookServiceImpl bookService;

	//図書登録
	@PostMapping("/bookcreate")
	public void insertBook(@RequestBody BookDTO bookDTO) {
		bookService.createBook(bookDTO);
	}

	//図書削除
	@DeleteMapping("/api/books/{bookId}")
	public void deleteBook(
			@PathVariable Long bookId) {
		bookService.deleteBook(bookId);
	}

	//図書修正
	@PutMapping("/api/books/{bookId}")
	public void updateBook(
			@PathVariable Long bookId,
			@RequestBody BookDTO bookDTO) {
		bookService.updateBook(bookDTO);
	}

	//図書リスト
	@GetMapping("/booklist")
	public List<BookDTO> bookList(@RequestParam(value = "title", required = false) String title) {

		return bookService.bookList(title);
	}
}