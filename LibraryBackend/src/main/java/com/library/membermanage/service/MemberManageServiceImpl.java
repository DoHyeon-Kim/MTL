package com.library.membermanage.service;

import org.springframework.stereotype.Service;

import com.library.bookmanage.dto.BookManageDTO;
import com.library.membermanage.dto.MemberLoanStatusDTO;
import com.library.membermanage.mapper.MemberManageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberManageServiceImpl implements MemberManageService {
	private final MemberManageMapper userManageMapper;

	//図書登録
	@Override
	public void createBook(BookManageDTO bookDTO) {

	}

	//図書削除
	@Override
	public void deleteBook(Long bookId) {
		// TODO 自動生成されたメソッド・スタブ

	}

	//図書修正
	@Override
	public void updateBook(BookManageDTO bookDTO) {
		// TODO 自動生成されたメソッド・スタブ

	}

	//図書リスト
	@Override
	public MemberLoanStatusDTO memberList(String memberId) {

		return userManageMapper.memberList(memberId);
	}

}
