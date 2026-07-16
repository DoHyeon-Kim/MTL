package com.library.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.library.book.dto.BookDTO;

@Mapper
public interface BookMapper {

	//図書登録
	void createBook(BookDTO bookDTO);
	//図書削除
	void deleteBoard(BookDTO bookId);
	//図書修正
	void updateBoard(BookDTO bookDTO);
	
	List<BookDTO> bookList(String title);
}
