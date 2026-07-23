package com.library.book.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.library.book.dto.BookDTO;
	
@Mapper
public interface BookMapper {
	
	List<BookDTO>BookList();
	
	List<BookDTO>BookSearch(String Search);
	
	List<BookDTO>BookDetail(Long bookNumberInfo);
	
	List<BookDTO>BookCategory();

	List<BookDTO>CategoryList(String category);
}
