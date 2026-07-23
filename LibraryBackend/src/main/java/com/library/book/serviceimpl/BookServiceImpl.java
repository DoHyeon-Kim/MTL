package com.library.book.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.book.dto.BookDTO;
import com.library.book.mapper.BookMapper;
import com.library.book.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
	
	private final BookMapper bookMapper;
	
	@Override
	public List<BookDTO>BookList() {
		return bookMapper.BookList();
	}
	
	@Override
	public List<BookDTO>BookSearch(String Search)
	{
		return bookMapper.BookSearch(Search);
	}
	
	@Override
	public List<BookDTO>BookDetail(Long bookNumberInfo)
	{
		return bookMapper.BookDetail(bookNumberInfo);
	}
	@Override
	public List<BookDTO>BookCategory()
	{
		return bookMapper.BookCategory();
	}
	
	@Override
	public List<BookDTO>CategoryList(String category)
	{
		return bookMapper.CategoryList(category);
	}
}
