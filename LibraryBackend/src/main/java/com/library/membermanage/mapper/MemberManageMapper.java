package com.library.membermanage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.library.bookmanage.dto.BookDTO;
import com.library.membermanage.dto.MemberLoanStatusDTO;

@Mapper
public interface MemberManageMapper {

	//図書登録
	void createBook(BookDTO bookDTO);

	//図書削除
	void deleteBoard(BookDTO bookId);

	//図書修正
	void updateBoard(BookDTO bookDTO);

	MemberLoanStatusDTO memberList(String memberId);
}
