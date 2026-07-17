package com.library.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.book.dto.BookDTO;
import com.library.book.dto.BookLoanStatusDTO;
import com.library.book.mapper.BookMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	private final BookMapper bookMapper;

	//図書登録
	@Override
	public void createBook(BookDTO bookDTO) {
		bookMapper.createBook(bookDTO);

	}

	//図書詳細
	@Override
	public BookDTO bookDetail(BookDTO bookDTO) {
		return bookMapper.bookDetail(bookDTO);
	}

	//図書在庫状態
	@Override
	public List<BookLoanStatusDTO> bookStockList(Long bookNumberInfo) {

		return bookMapper.bookStockList(bookNumberInfo);

	}

	//図書削除
	@Override
	public void deleteBook(Long bookId) {
		// TODO 自動生成されたメソッド・スタブ

	}

	//図書修正
	@Override
	public void updateBook(BookDTO bookDTO) {
		// TODO 自動生成されたメソッド・スタブ

	}

	//図書リスト
	@Override
	public List<BookDTO> bookList(String title) {
		return bookMapper.bookList(title);
	}

}
