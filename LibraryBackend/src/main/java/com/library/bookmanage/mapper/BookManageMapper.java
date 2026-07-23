package com.library.bookmanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.library.bookmanage.dto.BookLoanStatusDTO;
import com.library.bookmanage.dto.BookManageDTO;

@Mapper
public interface BookManageMapper {

	//図書登録
	void createBook(BookManageDTO bookDTO);

	//図書詳細
	BookManageDTO bookManageDetail(BookManageDTO bookManageDTO);

	//図書在庫状態
	List<BookLoanStatusDTO> bookStockList(Long bookNumberInfo);

	//図書削除
	void deleteBook(Long bookNumberInfo);

	//図書修正
	void updateManageBook(BookManageDTO BookManageDTO);

	//図書リスト
	List<BookManageDTO> bookList(String title);
	
	//図書在庫削除
	void bookDeleteStock(int BookNumber);
}
