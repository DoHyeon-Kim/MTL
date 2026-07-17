package com.library.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.library.book.dto.BookDTO;
import com.library.book.dto.BookLoanStatusDTO;

@Mapper
public interface BookMapper {

	//図書登録
	void createBook(BookDTO bookDTO);

	//図書詳細
	BookDTO bookDetail(BookDTO bookDTO);

	//図書在庫状態
	List<BookLoanStatusDTO> bookStockList(Long bookNumberInfo);

	//図書削除
	void deleteBoard(BookDTO bookId);

	//図書修正
	void updateBoard(BookDTO bookDTO);

	//図書リスト
	List<BookDTO> bookList(String title);
}
