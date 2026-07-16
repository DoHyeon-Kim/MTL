package com.library.membermanage.service;

import com.library.book.dto.BookDTO;
import com.library.membermanage.dto.MemberLoanStatusDTO;

public interface MemberManageService {

	public void createBook(BookDTO bookDTO);

	public void deleteBook(Long bookId);

	public void updateBook(BookDTO bookDTO);

	MemberLoanStatusDTO memberList(String memberId);
}
