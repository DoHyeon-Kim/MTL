package com.library.membermanage.service;

import com.library.bookmanage.dto.BookManageDTO;
import com.library.membermanage.dto.MemberLoanStatusDTO;

public interface MemberManageService {

	public void createBook(BookManageDTO bookDTO);

	public void deleteBook(Long bookId);

	public void updateBook(BookManageDTO bookDTO);

	MemberLoanStatusDTO memberList(String memberId);
}
