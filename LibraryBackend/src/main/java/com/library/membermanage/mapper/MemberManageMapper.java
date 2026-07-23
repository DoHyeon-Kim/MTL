package com.library.membermanage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.library.bookmanage.dto.BookManageDTO;
import com.library.membermanage.dto.MemberLoanStatusDTO;

@Mapper
public interface MemberManageMapper {

	//図書登録
	void createBook(BookManageDTO bookDTO);

	//図書削除
	void deleteBoard(BookManageDTO bookId);

	//図書修正
	void updateBoard(BookManageDTO bookDTO);

	MemberLoanStatusDTO memberList(String memberId);
}
