package com.library.bookmanage.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.bookmanage.dto.BookLoanStatusDTO;
import com.library.bookmanage.dto.BookManageDTO;
import com.library.bookmanage.mapper.BookManageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookManageServiceImpl implements BookManageService {
	private final BookManageMapper bookManageMapper;

	//図書登録
	@Override
	public void createBook(BookManageDTO bookDTO) {
		bookManageMapper.createBook(bookDTO);

	}

	//図書詳細
	@Override
	public BookManageDTO bookDetail(BookManageDTO bookDTO) {
		return bookManageMapper.bookDetail(bookDTO);
	}

	//図書在庫状態
	@Override
	public List<BookLoanStatusDTO> bookStockList(Long bookNumberInfo) {

		return bookManageMapper.bookStockList(bookNumberInfo);

	}

	//図書削除
	@Override
	public void deleteBook(Long bookNumberInfo) {

		bookManageMapper.deleteBook(bookNumberInfo);
	}

	//図書修正
	@Override
	public void updateBook(String bookId, BookManageDTO bookDTO, MultipartFile imageFile) {

		String uploadDir = "C:/uploads/images/";

		if (imageFile != null && !imageFile.isEmpty()) {
			String originalFileName = imageFile.getOriginalFilename();
			String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

			File saveFile = new File(uploadDir + storedFileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			try {
				imageFile.transferTo(saveFile);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("予期せぬエラーが発生しました.", e);
			}

			String imageUrl = "http://localhost:8099/uploads/" + storedFileName;

			bookDTO.setBookImg(imageUrl);

			bookManageMapper.updateBook(bookDTO);

		}
	}

	//図書リスト
	@Override
	public List<BookManageDTO> bookList(String title) {
		return bookManageMapper.bookList(title);
	}

	//図書在庫削除
	@Override
	public void bookDeleteStock(int bookNumber) {
		
		bookManageMapper.bookDeleteStock(bookNumber);
	}

}
